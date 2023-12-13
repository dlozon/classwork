using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AttackCone : MonoBehaviour
{
    private void OnCollisionEnter(Collision collision)
    {
        Debug.Log("collided with: " + collision.gameObject.name);
        if (collision.gameObject.tag.Equals("Enemy"))
            collision.gameObject.SendMessage("takeDamage", 10, SendMessageOptions.DontRequireReceiver);
    }
}
