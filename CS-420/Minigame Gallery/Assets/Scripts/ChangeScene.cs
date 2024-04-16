using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ChangeScene : MonoBehaviour
{
    public string sceneName;

    void Update()
    {
        // Add keyboard controls for testing
        #if UNITY_EDITOR
        if (Input.GetKeyDown(KeyCode.Space))
            ChangeToScene();
        #endif
    }
    

    public void ChangeToScene() {
        UnityEngine.SceneManagement.SceneManager.LoadScene(sceneName);
    }
    public void ChangeToScene(string sceneName) {
        UnityEngine.SceneManagement.SceneManager.LoadScene(sceneName);
    }
}
