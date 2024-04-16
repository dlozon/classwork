using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.InputSystem;

public class TargetBehavior : MonoBehaviour
{
    [Tooltip("The rings on the target will be changed to this material when hit.")]
    public Material hitMaterial;
    [Tooltip("The target will be destroyed when they fall below this height.")]
    public float destroyHeight = -1;
    [Tooltip("The target will be destroyed after this many seconds. 0 to disable.")]
    public float destroyTime = 15f;

    private Renderer targetRenderer;
    private GameManager gameManager;
    private bool hit = false;

    // Start is called before the first frame update
    void Start()
    {
        Destroy(gameObject, destroyTime);

        if (!GameObject.FindGameObjectWithTag("GameManager").TryGetComponent<GameManager>(out gameManager))
            Debug.LogError("No game manager found.");
        if (!TryGetComponent<Renderer>(out targetRenderer))
            Debug.LogError("Target has no renderer.");

        if (targetRenderer.materials.Length <= 0)
            Debug.LogError("Target has no materials.");
    }

    // Update is called once per frame
    void Update()
    {
        if (transform.position.y < destroyHeight)
            DestroySelf();

        // Add keyboard controls for testing
        #if UNITY_EDITOR
        if (Keyboard.current.downArrowKey.wasPressedThisFrame)
            Hit();
        #endif
    }

    // Defines program behavior when this target is shot
    private void Hit() {
        // Break out of the function early if target was already hit before
        if (hit)
            return;
        hit = true;
        
        gameManager.TargetHit();
        
        // Only change materials if one was provided
        if (hitMaterial != null)
            ChangeMaterial();
    }

    // Replace all materials except the first with hitMaterial
    private void ChangeMaterial() {
        Material[] materials = targetRenderer.materials;

        for (int i = 1; i < materials.Length; i++)
            materials[i] = hitMaterial;

        targetRenderer.materials = materials;
    }

    // Destroys this object
    private void DestroySelf() {
        if (!hit)
            gameManager.TargetMiss();

        Destroy(gameObject);
    }
}
