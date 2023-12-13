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

    private Renderer targetRenderer;
    private GameObject gameManager;
    private bool hit = false;

    // Start is called before the first frame update
    void Start() {
        gameManager = GameObject.FindGameObjectWithTag("GameManager");
        targetRenderer = GetComponent<Renderer>();

        if (!targetRenderer)
            Debug.LogError("Target has no renderer.");
        if (targetRenderer.materials.Length <= 0)
            Debug.LogError("Target has no materials.");
    }

    // Update is called once per frame
    void Update() {
        if (transform.position.y < destroyHeight)
            DestroySelf();

        if (Keyboard.current.downArrowKey.wasPressedThisFrame)
            Hit();
    }

    // Defines program behavior when this target is shot
    private void Hit() {
        // Break out of the function early if target was already hit before
        if (hit)
            return;
        hit = true;
        
        gameManager.SendMessage("TargetHit");
        
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
            gameManager.SendMessage("TargetMiss");

        Destroy(gameObject);
    }
}
