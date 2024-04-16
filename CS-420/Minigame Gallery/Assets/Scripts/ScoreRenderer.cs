using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class ScoreRenderer : MonoBehaviour
{
    [Tooltip("The TMP object that will display the current score")]
    public TextMeshPro score;
    [Tooltip("The TMP object that will display the current accuracy")]
    public TextMeshPro accuracy;
    [Tooltip("The TMP object that will display the high score")]
    public TextMeshPro highScore;
    [Tooltip("The TMP object that will display the best accuracy")]
    public TextMeshPro bestAccuracy;

    private GameManager gameManager;

    void Start() {
        gameManager = GameManager.Instance;
    }

    void Update() {
        if (score != null)
            score.text = gameManager.GetScore().ToString();
        if (accuracy != null)
            accuracy.text = (gameManager.GetAccuracy() * 100f).ToString("00.0");
        if (highScore != null)
            highScore.text = gameManager.GetHighScore().ToString();
        if (bestAccuracy != null)
            bestAccuracy.text = (gameManager.GetHighScoreAccuracy() * 100f).ToString("00.0");
            
    }
}
