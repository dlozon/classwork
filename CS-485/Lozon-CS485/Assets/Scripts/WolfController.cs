using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;
using UnityEngine.SceneManagement;

public class WolfController : MonoBehaviour
{
    private Animator animator;
    private GameObject player;
    private VisionCone visionCone;

    // When was last sniff
    private float sniffTimer = 0;
    // When should next sniff happen
    private float sniffTimerEnd = 0;

    // True if the player has ever been spotted
    private bool playerSeen = false;
    // How much time is left on cooldown
    private float cooldownRemaining = 0;
    // How much time is left on attack delay
    private float delayRemaining = 0;
    // Helper variable for delay tracking
    private bool hasAttacked = false;

    [HideInInspector]
    public float HP;

    // This layer should include anything the wolf is capable of
    // seeing, including the player
    public LayerMask obstacleLayer;

    [Header("Detection")]
    [Tooltip("How close a player can get before being smelled.")]
    public float detectionRange = 20;
    [Tooltip("Shortest possible time between sniffs.")]
    public float sniffTimerRangeMin = 5;
    [Tooltip("Longest possible time between sniffs.")]
    public float sniffTimerRangeMax = 8;

    [Header("Attack")]
    [Tooltip("How close a player can get before being hit.")]
    public float attackRange = 3;
    [Tooltip("How much damage a single strike will do.")]
    public float damage = 5;
    [Tooltip("How long between an attack being initiated and it connecting.")]
    public float attackDelay = .2f;
    [Tooltip("The minimum time between attacks.")]
    public float cooldownTime = 3;

    [Header("Other")]
    [Tooltip("The maximum amount of health this enemy can have.")]
    public float maxHP = 40;
    public float speed = 8;

    // Start is called before the first frame update
    void Start()
    {
        animator = GetComponent<Animator>();
        player = GameObject.FindWithTag("Player");

        // Get all vision cones in an array
        GameObject[] visionCones;
        visionCones = GameObject.FindGameObjectsWithTag("Vision Cone");

        // Search for the vision cone that belongs to this enemy
        foreach (GameObject cone in visionCones)
            if(cone.transform.IsChildOf(this.transform))
                visionCone = cone.GetComponent<VisionCone>();

        HP = maxHP;
        this.transform.rotation = Quaternion.Euler(0, Random.Range(0,360), 0);
        
    }

    // Update is called once per frame
    void Update()
    {
        if (HP <= 0)
            return;

        // Check if it's time to sniff
        if (sniffAnimationTimerUpdate())
        {
            // After a sniff, check if the player is near enough to be smelled
            isPlayerNearby();
        }


        if (playerSeen)
        {
            updateCooldown();
            isInRange(player);
            fight(player);
        }
        else
            checkVision();
    }

    // Keep track of the sniff timer
    private bool sniffAnimationTimerUpdate()
    {
        sniffTimer += Time.deltaTime;

        // If the timer has reached it's end, send a sniff signal and reset timer
        if (sniffTimer >= sniffTimerEnd)
        {
            // The amount of time between each sniff is random
            sniffTimerEnd = Random.Range(sniffTimerRangeMin, sniffTimerRangeMax);
            sniffTimer = 0;
            animator.SetBool("sniff", true);
            return true;
        }
        else
        {
            animator.SetBool("sniff", false);
            return false;
        }
    }

    // Check if the player is near
    private bool isPlayerNearby()
    {
        float dist = Vector3.Distance(this.transform.position, player.transform.position);
        if (dist < detectionRange)
        {
            animator.SetBool("playerNearby", true);
            return true;
        }
        else
        {
            animator.SetBool("playerNearby", false);
            return false;
        }
    }

    // Check if the player can be seen
    private bool checkVision()
    {
        if (visionCone.seesPlayer)
        {
            // Check if there are any obstacles inbetween this and the player
            Physics.Linecast(this.transform.position, player.transform.position, out RaycastHit hit, obstacleLayer);
            if (hit.collider.gameObject.Equals(player))
            {
                animator.SetBool("playerSeen", true);
                playerSeen = true;
                transform.LookAt(player.transform);
                return true;
            }
                return false;
        }
            return false;

    }

    // Run to target and attack it
    private void fight(GameObject target)
    {
        if (updateCooldown())
        {
            hasAttacked = true;
            return;
        }
            
        this.transform.LookAt(target.transform);
        
        if (isInRange(target))
        {
            attack(target, attackDelay);
        }
        else
        {
            float y = this.transform.position.y;
            this.transform.position += this.transform.forward * speed * Time.deltaTime;
            this.transform.position = new Vector3(this.transform.position.x, y, this.transform.position.z);
        }
        
    }
    
    // Check if the target is close enough to be attacked
    private bool isInRange(GameObject target)
    {
        float dist = Vector3.Distance(this.transform.position, target.transform.position);
        if (dist < attackRange)
        {
            animator.SetBool("inRange", true);
            return true;
        }
        else
        {
            animator.SetBool("inRange", false);
            return false;
        }
    }

    // Deals damage to target if they're in range after a delay
    private void attack(GameObject target, float delay)
    {
        if(hasAttacked)
        {
            delayRemaining = delay;
            hasAttacked = false;
        }

        if (!updateDelay())
        {
            if(isInRange(target))
                target.SendMessage("takeDamage", damage, SendMessageOptions.DontRequireReceiver);

            hasAttacked = true;
            cooldownRemaining = cooldownTime;
        }

    }

    // Keep track of the attack cooldown
    private bool updateCooldown()
    {
        if (cooldownRemaining > 0)
        {
            animator.SetBool("onCooldown", true);
            cooldownRemaining -= Time.deltaTime;
            return true;
        }
        else
        {
            animator.SetBool("onCooldown", false);
            return false;
        }
    }

    // Keep track of the attack cooldown
    private bool updateDelay()
    {
        if (delayRemaining > 0)
        {
            delayRemaining -= Time.deltaTime;
            return true;
        }
        else
        {
            return false;
        }
    }

    // Reduce HP
    private void takeDamage(float damage)
    {
        HP -= damage;
        if (HP <= 0)
            animator.SetBool("dead", true);
    }
}
