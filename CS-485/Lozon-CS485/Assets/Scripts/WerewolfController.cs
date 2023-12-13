using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;
using UnityEngine.SceneManagement;

public class WerewolfController : MonoBehaviour
{
    private Animator animator;
    private GameObject player;

    // How much time is left on cooldown
    private float cooldownRemaining = 0;
    // How much time is left on attack delay
    private float delayRemaining = 0;
    // Helper variable for delay tracking
    private bool hasAttacked = false;

    [HideInInspector]
    public float HP;

    [Header("Attack")]
    [Tooltip("How close a player can get before being hit.")]
    public float attackRange = 5;
    [Tooltip("How much damage a single strike will do.")]
    public float damage = 15;
    [Tooltip("How long between an attack being initiated and it connecting.")]
    public float attackDelay = .5f;
    [Tooltip("The minimum time between attacks.")]
    public float cooldownTime = 5;

    [Header("Other")]
    [Tooltip("The maximum amount of health this enemy can have.")]
    public float maxHP = 120;
    public float speed = 12;

    // Start is called before the first frame update
    void Start()
    {
        animator = GetComponent<Animator>();
        player = GameObject.FindWithTag("Player");

        // Get all vision cones in an array
        GameObject[] visionCones;
        visionCones = GameObject.FindGameObjectsWithTag("Vision Cone");

        HP = maxHP;
        this.transform.rotation = Quaternion.Euler(0, Random.Range(0, 360), 0);

    }

    // Update is called once per frame
    void Update()
    {
        if (HP <= 0)
            return;

        updateCooldown();
        isInRange(player);
        fight(player);
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
        if (hasAttacked)
        {
            delayRemaining = delay;
            hasAttacked = false;
        }

        if (!updateDelay())
        {
            if (isInRange(target))
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
        {
            animator.SetBool("dead", true);
            SceneManager.LoadScene("Win Menu", LoadSceneMode.Single);
        }
    }
}
