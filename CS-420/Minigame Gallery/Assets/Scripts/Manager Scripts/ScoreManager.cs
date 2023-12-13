using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using UnityEditor;
using UnityEngine;

public class ScoreManager
{
    // This is a container that stores persistent score data
    public class PersistentScores {
        public string SG_playerName;
        public int SG_highScore;
        public float SG_highScoreAccuracy;
        public int SG_totalHits;
    }
    private readonly PersistentScores scoreData;
    private readonly string FilePath;

    public int TargetValue {get; set;}

    private int SG_currentHits = 0;
    private int SG_currentMisses = 0;
    private int SG_currentScore = 0; 
    
    // Construct a score manager
    public ScoreManager(string FileDir, string FileName) {
        FilePath = FileDir + FileName;
        Debug.Log("ScoreManager created with scorefile: " + FilePath);

        scoreData = LoadScoreData(FilePath);
    }

    // Create a file to store scores in
    PersistentScores CreateShootingGalleryScoreFile(string filePath) {
        PersistentScores newScoreData = new()
        {
            SG_playerName = "No Score Yet!",
            SG_highScore = 0,
            SG_highScoreAccuracy = 0,
            SG_totalHits = 0
        };

        string json = JsonUtility.ToJson(newScoreData);
        File.WriteAllText(filePath, json);
        Debug.Log("New score file created with default values.");

        return newScoreData;
    }

    // Load score data from a file
    PersistentScores LoadScoreData(string filePath) {
        // If the score file does not exist, make a new one
        if (!File.Exists(filePath))
            CreateShootingGalleryScoreFile(filePath);

        // Load JSON data from the existing file
        string json = File.ReadAllText(filePath);
        PersistentScores loadedData = JsonUtility.FromJson<PersistentScores>(json);
        Debug.Log("Scores loaded successfully.");

        return loadedData;
    }

    // Save score data to a file
    void SaveShootingGalleryScoreData(string filePath) {
        // If the score file does not exist, make a new one
        if (!File.Exists(filePath))
            CreateShootingGalleryScoreFile(filePath);

        // Load existing score data
        PersistentScores existingScoreData = LoadScoreData(filePath);

        // Update the shooting gallery score data
        existingScoreData.SG_playerName = scoreData.SG_playerName;
        existingScoreData.SG_highScore = scoreData.SG_highScore;
        existingScoreData.SG_highScoreAccuracy = scoreData.SG_highScoreAccuracy;
        existingScoreData.SG_totalHits = scoreData.SG_totalHits;

        // Serialize and save the updated data
        string updatedJson = JsonUtility.ToJson(existingScoreData);
        File.WriteAllText(filePath, updatedJson);
        Debug.Log("Shooting gallery scores saved successfully.");
    }
    
    // Start a shooting gallery minigame and reset the current score
    public void ShootingGalleryStart() {
        SG_currentHits = 0;
        SG_currentMisses = 0; 
        SG_currentScore = 0;
    }
    
    public void TargetHit() {
        scoreData.SG_totalHits++;
        SG_currentHits++; 
        SG_currentScore += TargetValue;
        Debug.Log("A target was hit");
    }
    
    public void TargetMiss() {
        SG_currentMisses++;
        Debug.Log("A target was missed");
    }

    // End a shooting gallery minigame save new score data
    public void ShootingGalleryEnd() {
        // Update the high score if the current score is higher
        if (SG_currentScore > scoreData.SG_highScore){
            scoreData.SG_highScore = SG_currentScore;   
            scoreData.SG_highScoreAccuracy = (float)SG_currentHits / (SG_currentHits + SG_currentMisses);

            Debug.Log("New High Score is " + scoreData.SG_highScore);
        }

        SaveShootingGalleryScoreData(FilePath);
    }
}
