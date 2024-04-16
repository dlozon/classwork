using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

public class BulletBehavior : MonoBehaviour
{
    [Tooltip("Whether the bullet should be destroyed when it hits something.")]
    public bool destroyOnCollision = false;
    [Tooltip("The bullet will be destroyed if it travels this far from its spawn point. 0 to disable.")]
    public float destroyDistance = 0f;
    [Tooltip("The bullet will be destroyed after this many seconds.0 to disable.")]
    public float destroyTime = 5f;

    private Vector3 spawnPoint;
    private bool firstCollision = true;

    void Start() {
        // Record the spawn point
        spawnPoint = transform.position;
        // Destroy the bullet after a certain amount of time
        if (destroyTime > 0)
            Destroy(gameObject, destroyTime);
    }
    
    void Update() {
        // Destroy the bullet if it travels too far from its spawn point
        if (destroyDistance > 0 && Vector3.Distance(transform.position, spawnPoint) > destroyDistance)
            Destroy(gameObject);
    }

    void OnCollisionEnter(Collision collision)
    {
        // Ignore collisions after the first one
        if (!firstCollision)
            return;

        // Notify the object that was hit and log the collision
        collision.gameObject.SendMessage("Hit", SendMessageOptions.DontRequireReceiver);
        Debug.Log(collision.gameObject.name + " was shot.");
        firstCollision = false;

        // Destroy the bullet
        if (destroyOnCollision)
            Destroy(gameObject);
    }
}
