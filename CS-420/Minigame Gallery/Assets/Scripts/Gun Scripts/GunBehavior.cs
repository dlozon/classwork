using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.InputSystem;

public class GunBehavior : MonoBehaviour
{
    [Tooltip("If true, the gun will fire a bullet. Otherwise, it will perform a raycast")]
    public bool useBullet = true;

    [Tooltip("Copies of this object will be created and launched.")]
    public GameObject bulletObject;
    [Tooltip("The force applied to a bullet when the gun is fired")]
    public float launchForce = 10f;
    [Tooltip("The bullet will spawn at this point relative to the position of the gun.")]
    public Transform bulletSpawnPoint;

    [Tooltip("The sound that plays when the gun is fired.")]
    public AudioClip shootSound;
    [Range(0f, 1f)]
    public float shootVolume = 1f;

    private AudioSource audioSource;

    private void Start() {
        // Add an AudioSource component to the GameObject
        audioSource = gameObject.AddComponent<AudioSource>();
        // Assign the shoot sound to the AudioSource
        audioSource.clip = shootSound;

        if (shootSound == null)
            Debug.LogWarning("No shoot sound assigned to the gun.");
    }

    private void Update() {
        // Controls intended for testing use
        if (Keyboard.current.eKey.wasPressedThisFrame)
            Shoot();
    }

    public void Shoot() {
        // Play the shoot sound
        if (shootSound != null)
            audioSource.PlayOneShot(shootSound, shootVolume);

        if (useBullet)
            ShootBullet();
        else
            ShootRaycast();
    }

    private void ShootBullet() {
        // Spawn a bullet at the given position
        GameObject bullet = Instantiate(bulletObject, bulletSpawnPoint.position, bulletSpawnPoint.rotation);
        bullet.transform.parent = null; // Disown the bullet

        // Apply a force to the bullet
        if (bullet.TryGetComponent<Rigidbody>(out var rb))
            rb.AddForce(bulletSpawnPoint.forward * launchForce, ForceMode.Impulse);
        else
            Debug.LogError("The bullet doesn't have a Rigidbody component.");
    }

    private void ShootRaycast() {
        // Simulate a shot being fired by raycasting
        if (Physics.Raycast(transform.position, transform.forward, out RaycastHit hit)) {
            // Get the GameObject that was hit
            GameObject hitObject = hit.collider.gameObject;

            // Tell the object it was hit
            hitObject.SendMessage("Hit", SendMessageOptions.DontRequireReceiver);
            // Log the hit
            Debug.Log(hitObject.name + " was shot.");
        }
    }
}
