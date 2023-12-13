using UnityEditor;
using UnityEngine;
using UnityEngine.InputSystem;
using UnityEngine.SceneManagement;

public class PlayerController : MonoBehaviour
{
    private Vector2 movementVector;
    private Vector2 mouseDelta;
    private Rigidbody rb;
    private Transform cam;
    private Animator sword;
    private GameObject attackCone;
    private bool sprinting;
    private int allowedJumps;
    private int allowedRepulses;
    private float cooldownRemaining = 0;

    [HideInInspector]
    public float stamina;
    [HideInInspector]
    public float HP;

    [Tooltip("When the player is standing on an object in this layer, they are considered grounded.")]
    public LayerMask groundLayer;
    [Tooltip("Objects that can be interacted with.")]
    public LayerMask interactableLayer;
    [Tooltip("Enemies that can be attacked.")]
    public LayerMask enemyLayer;

    [Header("Sensetivity")]
    [Range(.01f, 1)]
    [Tooltip("Horizontal camera sensetivity.")]
    public float xSens = .2f;
    [Tooltip("Vertical camera sensetivity.")]
    [Range(.01f, 1)]
    public float ySens = .2f;

    [Header("Movement")]
    [Tooltip("The movement speed of the player.")]
    public float moveSpeed = 5;
    [Tooltip("The player's movement speed is multiplied by this while sprinting.")]
    public float sprintMultiplier = 1.5f;
    [Tooltip("The maximum amount of time a player can sprint for before stopping.")]
    public float maxSprintTime = 8;
    [Tooltip("Stamina Regen Multiplier = Stamina Regenerated / Time")]
    public float staminaRegenMultiplier = 2;
    [Tooltip("The force applied by a jump.")]
    public float jumpPower = 5;
    [Tooltip("The maximum amount of jumps the player is allowed to make before grounding.")]
    public int maxJumps = 2;
    [Tooltip("The force applied by the repulse ability.")]
    public float repulsePower = 7f;
    [Tooltip("The maximum amount of repulses the player is allowed to use before grounding.")]
    public int maxRepulses = 1;

    [Header("Attack")]
    [Tooltip("How much damage a single strike will do.")]
    public float damage = 5;
    [Tooltip("The minimum time between attacks.")]
    public float cooldownTime = .5f;

    [Header("Other")]
    [Tooltip("The maximum amount of health the player can have.")]
    public float maxHP = 100;
    [Tooltip("When the player is looking at an object in this layer, they can try to interact with it.")]
    public float interactRange = 2.5f;


    // This script controls the user's ability to interact with the player character
    // For example, movement, camera control, and interactions are all handled here

    void Start()
    {
        allowedJumps = maxJumps;
        cam = GameObject.FindWithTag("Camera").transform;
        rb = GetComponent<Rigidbody>();
        sword = GameObject.FindWithTag("Sword").GetComponent<Animator>();
        attackCone = GameObject.FindWithTag("Attack Cone");

        stamina = maxSprintTime;
        HP = maxHP;
    }

    void Update()
    {
        // Give the player camera control
        look();
        updateStamina();

        Debug.DrawLine(cam.position, cam.position + (cam.forward * interactRange), Color.red, .1f);

        // If the sprint button is being held, set the player to sprinting speed
        if (sprinting)
            move(movementVector.x * sprintMultiplier, movementVector.y * sprintMultiplier);
        else
            move(movementVector.x, movementVector.y);

        // Refresh the player's jumps if they're grounded
        if (isGrounded())
        {
            allowedJumps = maxJumps;
            allowedRepulses = maxRepulses;
        }
        else if (!isGrounded() && allowedJumps == maxJumps)
            allowedJumps--;

        updateCooldown();
    }

    // Makes the player's view follow the mouse
    void look()
    {
        // Change in mouse position since last frame
        mouseDelta = Mouse.current.delta.ReadValue();

        // Rotate the character horizontally
        this.transform.localEulerAngles =
            new Vector3(0, transform.localEulerAngles.y + (mouseDelta.x * xSens / 2), 0);

        // Rotate the camera vertically
        cam.localEulerAngles = new Vector3(cam.localEulerAngles.x - (mouseDelta.y * ySens / 2), 0, 0);
    }

    // Update the movement instructions
    void OnMove(InputValue value)
    {
        movementVector = value.Get<Vector2>();
    }
    // Change the velocity of the player character
    void move(float x, float y)
    {
        Vector3 movementVector =
            ((transform.right * x + transform.forward * y) * moveSpeed)
            + (transform.up * rb.velocity.y);

        // Take some control away from the player when in air
        if (!isGrounded())
            rb.velocity = (movementVector * Time.deltaTime) + rb.velocity;
        else
            rb.velocity = movementVector;
    }

    // Keep track of whether the player is sprinting
    void OnSprint(InputValue value)
    {
        sprinting = value.isPressed;
        Debug.Log("sprinting: " + sprinting);
    }

    // Launch the player up with an impulse
    void OnJump(InputValue value)
    {
        if (allowedJumps > 0)
        {
            // Reset vertical velocity to 0 before launch
            rb.velocity = new Vector3(rb.velocity.x, 0, rb.velocity.z);
            rb.AddForce(Vector3.up * jumpPower, ForceMode.Impulse);
            allowedJumps--;
        }
    }

    // Send an activate signal to interactables that the player is looking at
    void OnInteract()
    {
        // Cast a ray from the player's camera and check if it is colliding with an interactable object
        Physics.Raycast(cam.position, cam.forward, out RaycastHit hit, 2.5f, interactableLayer);

        if (hit.collider.gameObject != null)
        {
            Debug.Log("Attempted to interact with: " + hit.collider.gameObject.name);
            hit.collider.gameObject.SendMessage("Activate", null, SendMessageOptions.DontRequireReceiver);
        }
    }

    // Check if the player is colliding with the ground
    private bool isGrounded()
    {
        // Create a sphere below the player and check if it is colliding with something in the ground layer
        Vector3 spherePosition = new Vector3(transform.position.x, transform.position.y - .65f, transform.position.z);
        bool grounded = Physics.CheckSphere(spherePosition, .48f, groundLayer, QueryTriggerInteraction.Ignore);

        return grounded;
    }

    // Keeps track of stamina so the player has to manage their sprinting
    private void updateStamina()
    {
        if (sprinting)
            stamina -= Time.deltaTime;
        else
            stamina += Time.deltaTime * staminaRegenMultiplier;

        if (stamina > maxSprintTime)
            stamina = maxSprintTime;

        if (stamina <= 0)
            sprinting = false;
    }

    // Repulse ability launches the player in the opposite direction that they're facing
    void OnRepulse()
    {
        if (allowedRepulses > 0)
        {
            // Reset velocity to 0 before launch
            rb.velocity = Vector3.zero;
            rb.AddForce(cam.forward * -1 * repulsePower, ForceMode.Impulse);
            allowedRepulses -= 1;
        }
    }

    // Reduce player's HP
    private void takeDamage(float damage)
    {
        HP -= damage;
        if(HP <= 0)
            SceneManager.LoadScene("Lose Menu", LoadSceneMode.Single);
    }

    // Keep track of the attack cooldown
    private bool updateCooldown()
    {
        if (cooldownRemaining > 0)
        {
            if (cooldownRemaining > cooldownTime * .95)
            {
                cooldownRemaining -= Time.deltaTime;
                return true;
            }
            cooldownRemaining -= Time.deltaTime;
            sword.SetBool("isAttacking", false);
            return true;
        }
        return false;
    }

    void OnAttack()
    {
        // Cast a ray from the player's camera and check if it is colliding with an enemy
        Physics.Raycast(cam.position, cam.forward, out RaycastHit hit, 5f, enemyLayer);
        
        if (cooldownRemaining <= 0 && hit.collider.gameObject != null)
        {
            Debug.Log("Attempted to hit: " + hit.collider.gameObject.name);
            sword.SetBool("isAttacking", true);
            hit.collider.gameObject.SendMessage("takeDamage", damage, SendMessageOptions.DontRequireReceiver);
            cooldownRemaining = cooldownTime;
        }
    }
}