using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.InputSystem;

public class GunBehavior : MonoBehaviour
{
    [Tooltip("Copies of this object will be created and launched.")]
    public GameObject bulletObject;
    // The force applied to a bullet when the gun is fired
    public float launchForce = 10f;

    // The bullet will spawn at this point relative to the position of the gun
    readonly private Vector3 bulletSpawnOffset = new(0.25f, 0.015f, .0f);

    private void Update() {
        // Controls intended for testing use
        if (Keyboard.current.eKey.wasPressedThisFrame)
            Shoot();
    }

    void Shoot() {
        // Spawn a bullet at an offset relative to the gun
        Vector3 spawnPosition = transform.position + transform.TransformDirection(bulletSpawnOffset);
        GameObject bullet = Instantiate(bulletObject, spawnPosition, transform.rotation);

        // Apply a force to the bullet
        if (bullet.TryGetComponent<Rigidbody>(out var rb))
            rb.AddForce(transform.right * launchForce, ForceMode.Impulse);
        else
            Debug.LogWarning("The spawned object doesn't have a Rigidbody component.");
    }
}
