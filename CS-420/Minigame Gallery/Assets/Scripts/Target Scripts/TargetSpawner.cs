using System.Collections;
using UnityEditor.EditorTools;
using UnityEngine;
using UnityEngine.InputSystem;

public class TargetSpawner : MonoBehaviour
{
    [Tooltip("Copies of this object will be created and launched.")]
    public GameObject targetObject;

    [Tooltip("This represents where the targets will spawn.")]
    public Transform shootFrom;
    [Tooltip("This represents where the targets will be shot to.")]
    public Transform shootAt;

    public float launchForce = 10f;

    private void Update() {
        // Controls intended for testing use
        if (Keyboard.current.upArrowKey.wasPressedThisFrame)
            Activate();
    }

    private void Activate() {
        // Create a copy of the target using the given transform
        GameObject newObject = Instantiate(targetObject, shootFrom.position, shootFrom.rotation);

        // Launch the target
        if (newObject.TryGetComponent<Rigidbody>(out var rb)) {
            Vector3 launchDirection = (shootAt.position - shootFrom.position).normalized;
            rb.AddForce(launchDirection * launchForce, ForceMode.Impulse);
        }
        else
            Debug.LogWarning("The spawned object doesn't have a Rigidbody component.");
    }
}