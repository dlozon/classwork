using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// <summary>
/// This script makes an object always face the player when they are nearby
/// </summary>
public class LookAtPlayer : MonoBehaviour
{
    [Tooltip("If the player is further than threshold, this will stop looking at them")]
    [Min(0f)]
    public float lookThreshold = 15;

    private GameObject player;
    private Quaternion initialRotation;

    // Start is called before the first frame update
    void Start()
    {
        player = GameObject.FindGameObjectWithTag("Player");
        
        if (player == null) {
            Debug.LogError("No object tagged with player was found.");
            Destroy(this);
        }

        initialRotation = transform.rotation;
    }

    // Update is called once per frame
    void Update()
    {
        if (GetPlayerDistance() < lookThreshold)
            TurnTowardPlayer();
        else
            transform.rotation = initialRotation;
    }

    /// <summary>
    /// Returns the distance between this object and the player
    /// </summary>
    private float GetPlayerDistance() {
        return Vector3.Distance(transform.position, player.transform.position);
    }

    /// <summary>
    /// Modify this object's rotation about the y axis to face the player
    /// </summary>
    private void TurnTowardPlayer() {
        // Find the direction vector pointing at the player
        Vector3 faceDirection = player.transform.position - transform.position;

        // Calculate the rotation to look at the player
        Quaternion rotation = Quaternion.LookRotation(faceDirection);
        rotation.x = 0; rotation.z = 0;

        // Apply the rotation to the object's transform
        transform.rotation = rotation;
    }
}
