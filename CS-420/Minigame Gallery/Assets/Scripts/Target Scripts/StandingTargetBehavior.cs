using System;
using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;
using UnityEngine.InputSystem;

public class StandingTargetBehavior : MonoBehaviour
{
    [Tooltip("The rings on the target will be changed to this material when hit.")]
    public Material hitMaterial;
    [Tooltip("The rings on the target will be changed to this material when it deactivates.")]
    public Material missMaterial;

    [Tooltip("The target can stand for this long before deactivating.")]
    public float maxStandTime = 5;

    private GameObject gameManager;
    private Animator targetAnimator;
    private Renderer targetRenderer;
    private Material[] originalMaterials;
    private bool standing;
    private float startedStanding;

    // Start is called before the first frame update
    void Start() {
        gameManager = GameObject.FindGameObjectWithTag("GameManager");
        targetAnimator = GetComponent<Animator>();
        targetRenderer = GetComponent<Renderer>();

        // Check for missing components
        if (!targetRenderer)
            Debug.LogError("Standing target has no renderer component.");
        if (!targetAnimator)
            Debug.LogError("Standing target has no animator component.");
        if (targetRenderer.materials.Length <= 0)
            Debug.LogError("Standing target has no materials.");

        originalMaterials = targetRenderer.materials;
    }

    // Update is called once per frame
    void Update() {
        // Controls intended for testing use
        if (Keyboard.current.upArrowKey.wasPressedThisFrame)
            Activate();
        if (Keyboard.current.downArrowKey.wasPressedThisFrame)
            Hit();

        // If the target has been standing too long, deactivate it
        if (standing && Time.time - startedStanding > maxStandTime) {
            Deactivate();
        }
    }

    // Defines program behavior when this target should stand
    void Activate() {
        // Do nothing if target was already activated
        if (standing)
            return;

        ChangeMaterials(originalMaterials);
        
        standing = true;
        startedStanding = Time.time;
        targetAnimator.ResetTrigger("FallTrigger");
        targetAnimator.SetTrigger("StandTrigger");
    }

    // Defines program behavior when this target is shot
    void Hit() {
        // Break out of the function early if target was already hit before
        if (!standing)
            return;
        
        gameManager.SendMessage("TargetHit");
        
        // Only change materials if one was provided
        if (hitMaterial != null) {
            ChangeRingsMaterial(hitMaterial);
        }        
        
        standing = false;
        targetAnimator.ResetTrigger("StandTrigger");
        targetAnimator.SetTrigger("FallTrigger");
    }

    // Defines program behavior when this target is shot
    private void Deactivate() {
        if (missMaterial != null) {
            ChangeRingsMaterial(missMaterial);
        }

        gameManager.SendMessage("TargetMiss");

        standing = false;
        targetAnimator.ResetTrigger("StandTrigger");
        targetAnimator.SetTrigger("FallTrigger");
    }

    // Change which materials are being used to render the target's rings
    private void ChangeRingsMaterial(Material newMaterial) {
        Material[] newMaterials = targetRenderer.materials;

        // Materials after the second one are assumed to be for the rings on the target 
        for (int i = 2; i < originalMaterials.Length; i++)
            newMaterials[i] = newMaterial;
        
        ChangeMaterials(newMaterials);
    }

    // Change which materials are being used to render the target
    private void ChangeMaterials(Material[] newMaterials) {
        targetRenderer.materials = newMaterials;
    }
}
