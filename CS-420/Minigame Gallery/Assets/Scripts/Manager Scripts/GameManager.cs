using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.InputSystem;

public class GameManager : MonoBehaviour
{
    private ScoreManager scoreManager;
    private TargetMinigameManager targetManager;

    [Tooltip("Scores persist in this directory.")]
    public string scoreFileDir = "./";
    [Tooltip("Scores persist under this filename.")]
    public string scoreFileName = "shooting_gallery_scores.json";
    [Tooltip("How many points a target is worth.")]
    public int targetValue = 1;

    void Awake() {
        DontDestroyOnLoad(this);

        // Create a score manager
        scoreManager = new(scoreFileDir, scoreFileName) {
            TargetValue = targetValue
        };

    }

    // Update is called once per frame
    void Update() {
        if (Keyboard.current.escapeKey.wasPressedThisFrame) {
            Application.Quit();
            Debug.Log("Escape key pressed, exiting game...");
        }

        if (Keyboard.current.enterKey.wasPressedThisFrame && !targetManager) {
            StartShootingGalleryMinigame();
        }

        if (Keyboard.current.backspaceKey.wasPressedThisFrame && targetManager) {
            EndShootingGalleryMinigame();
        }
    }


    // Start a shooting gallery minigame
    public void StartShootingGalleryMinigame() {
        if (targetManager) {
            Debug.LogWarning("A shooting gallery minigame is already active.");
            return;
        }

        targetManager = gameObject.AddComponent<TargetMinigameManager>();
        targetManager.MinTime = 1;
        targetManager.MaxTime = 3;

        targetManager.StartMinigame();
        scoreManager.ShootingGalleryStart();
    }

    // Update the score if a shooting gallery minigame is active
    public void TargetHit() { if (targetManager) scoreManager.TargetHit(); }
    public void TargetMiss() { if (targetManager) scoreManager.TargetMiss(); }

    // End a shooting gallery minigame
    public void EndShootingGalleryMinigame() {
        if (!targetManager) {
            Debug.LogWarning("No shooting gallery minigame active.");
            return;
        }

        targetManager.EndMinigame();
        Destroy(targetManager);

        scoreManager.ShootingGalleryEnd();
    }
}
