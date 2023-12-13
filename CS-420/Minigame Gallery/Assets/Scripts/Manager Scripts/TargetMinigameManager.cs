using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TargetMinigameManager : MonoBehaviour
{
    public float MinTime {get; set;}
    public float MaxTime {get; set;}

    private GameObject[] targets;

    // Constructor
    public TargetMinigameManager() {}

    public void StartMinigame() {
        Debug.Log("Shooting Gallery minigame is starting...");
        targets = GameObject.FindGameObjectsWithTag("Target");

        // If there are no targets, print an error and exit
        if (targets.Length <= 0) {
            Debug.Log("There are no objects tagged with \"Target\"");
            return;
        }

        // Start the coroutine to activate targets at random intervals
        StartCoroutine(ActivateTargetsWithRandomInterval());
    }

    public void EndMinigame() {
        Debug.Log("Shooting Gallery minigame has ended.");
        StopCoroutine(ActivateTargetsWithRandomInterval());
    }

    // Coroutine to activate targets at random intervals
    private IEnumerator ActivateTargetsWithRandomInterval() {
        while (true) {
            // Wait for a random time between minTime and maxTime
            float timeInterval = Random.Range(MinTime, MaxTime);
            yield return new WaitForSeconds(timeInterval);

            // Pick a random target to activate
            int randomIndex = Random.Range(0, targets.Length);
            GameObject randomTarget = targets[randomIndex];
            
            // Activate the target
            randomTarget.SendMessage("Activate");
        }
    }
}
