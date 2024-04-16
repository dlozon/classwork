using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuitGame : MonoBehaviour
{
    void Update() {
        // Add keyboard controls for testing
        #if UNITY_EDITOR
        if (Input.GetKeyDown(KeyCode.Escape))
            Quit();
        #endif
    }

    public void Quit() {
        Application.Quit();
    }
}
