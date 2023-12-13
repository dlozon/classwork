using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Menu : MonoBehaviour
{
    public void PlayGame()
    {
        SceneManager.LoadScene("Main Stage", LoadSceneMode.Single);
    }

    public void QuitGame()
    {
        Application.Quit();
    }
}
