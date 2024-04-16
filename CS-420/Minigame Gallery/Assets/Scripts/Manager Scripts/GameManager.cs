using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManager : MonoBehaviour
{
    public static GameManager Instance {get; private set;} = null;

    private ScoreManager scoreManager;
    private TargetMinigameManager targetManager;

    [Tooltip("Scores persist under this filename.")]
    public string scoreFileName = "shooting_gallery_scores.json";
    [Tooltip("How many points a target is worth.")]
    public int targetValue = 1;

    void Awake() {
        // Enforce singleton design pattern
        if (Instance == null) { // If there is no game manager, make this the game manager and initialize it
            Instance = this;
            scoreManager = new(Application.persistentDataPath, scoreFileName) { TargetValue = targetValue };
            DontDestroyOnLoad(gameObject);
        } 
        else if (Instance != this) { // If a game manager already exists, destroy this one
            Debug.LogWarning("Something attempted to create a second game manager. Destroying it..");
            Destroy(gameObject);
            return;
        }
    }

    public int GetScore() { return scoreManager.GetShootingGalleryCurrentScore(); }
    public float GetAccuracy() { return scoreManager.GetShootingGalleryCurrentAccuracy(); }
    public int GetHighScore() { return scoreManager.GetShootingGalleryHighScore(); }
    public float GetHighScoreAccuracy() { return scoreManager.GetShootingGalleryBestAccuracy(); }

    public void QuitGame() { Application.Quit(); }
    public void ChangeScene(string sceneName) { UnityEngine.SceneManagement.SceneManager.LoadScene(sceneName); }
    public bool IsGameActive() { return targetManager != null; }

    public void StartShootingGalleryMinigame(int time = 0) {
        targetManager = gameObject.AddComponent<TargetMinigameManager>();
        targetManager.MinTime = .75f;
        targetManager.MaxTime = 2.5f;

        targetManager.StartMinigame();
        scoreManager.ShootingGalleryStart();

        if (time > 0)
            Invoke(nameof(EndShootingGalleryMinigame), time);
    }

    public void TargetHit() { if (IsGameActive()) scoreManager.TargetHit(); }
    public void TargetMiss() { if (IsGameActive()) scoreManager.TargetMiss(); }

    public void EndShootingGalleryMinigame() {
        scoreManager.ShootingGalleryEnd();

        targetManager.EndMinigame();
        Destroy(targetManager);
    }
}
