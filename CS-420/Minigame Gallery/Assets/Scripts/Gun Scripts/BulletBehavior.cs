using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BulletBehavior : MonoBehaviour
{
    [Tooltip("The bullet will be destroyed when it falls below this height.")]
    public float destroyHeight = -1;

    void Update() {
        // Destroy the bullet if it falls below the destroy threshold
        if (transform.position.y < destroyHeight)
            Destroy(gameObject);
    }

    void OnTriggerEnter(Collider collision){
        // Send a message to the object that was hit
        collision.gameObject.SendMessage("Hit", SendMessageOptions.DontRequireReceiver);

        // Log the collision
        Debug.Log(collision.gameObject.name + " was shot.");

        // Destroy the bullet
        Destroy(gameObject);
    }
}
