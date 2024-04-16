using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TargetMinigameManager : MonoBehaviour
{
    public float MinTime {get; set;}
    public float MaxTime {get; set;}
    public int maxMultiTarget = 2;

    private GameObject[] targets;

    // Constructor
    public TargetMinigameManager() {}

    public void StartMinigame()
    {
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

        foreach (GameObject target in targets)
            target.SendMessage("Deactivate", SendMessageOptions.DontRequireReceiver);

        StopCoroutine(ActivateTargetsWithRandomInterval());
    }

    // Coroutine to activate targets at random intervals
    private IEnumerator ActivateTargetsWithRandomInterval() {
        while (true) {
            int targets = Random.Range(1, maxMultiTarget + 1);

            // Wait for a random time between minTime and maxTime
            float timeInterval = Random.Range(MinTime, MaxTime);
            yield return new WaitForSeconds(timeInterval);

            // Activate random targets
            for (int i = 0; i < targets; i++) {
                int randomIndex = Random.Range(0, this.targets.Length);
                GameObject randomTarget = this.targets[randomIndex];
                
                randomTarget.SendMessage("Activate");
            }
        }
    }
    
}
