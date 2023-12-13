using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Exit : MonoBehaviour
{
    void Activate()
    {
        SceneManager.LoadScene("Boss Stage", LoadSceneMode.Single);
    }
}
