using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.InputSystem;


public class MinigameController : MonoBehaviour
{
    [Tooltip("The TMP object that will display text")]
    public TextMeshPro tmp;
    [Tooltip("The color of the text when hovered over")]
    public Color hoveredColor;
    [Tooltip("The color of the text when not hovered over")]
    public Color unhoveredColor;

    private GameManager gameManager;
    private bool gamePlayed = false;
    private float minigameStartTime;

    void Start() {
        if (!GameObject.FindGameObjectWithTag("GameManager").TryGetComponent<GameManager>(out gameManager)) {
            tmp.text = "ERROR";
        }

        if (tmp == null) {
            Debug.LogError("Sign has no TextMeshPro object.");
            Destroy(this);
        }
    }

    void Update() {
        // Add keyboard controls for testing
        #if UNITY_EDITOR
        if (Keyboard.current.enterKey.wasPressedThisFrame)
            ControlMinigame();
        #endif
        
        if (gameManager.IsGameActive()) {
            tmp.text = (90 - (Time.time - minigameStartTime)).ToString("0.00");
            gamePlayed = true;
        }
        else if (gamePlayed)
            tmp.text = "AGAIN";
        else
            tmp.text = "PLAY";
    }

    public void Hit() {
        ControlMinigame();
    }

    public void ControlMinigame() {
        if (gameManager.IsGameActive())
            gameManager.EndShootingGalleryMinigame();
        else {
            gameManager.StartShootingGalleryMinigame(90);
            minigameStartTime = Time.time;
        }
    }

    public void ChangeColor(bool hovered) {
        if (hovered)
            tmp.color = hoveredColor;
        else
            tmp.color = unhoveredColor;
    }
}
