using InsaneScatterbrain.ScriptGraph;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LevelGenerator : MonoBehaviour
{
    [SerializeField] private ScriptGraphRunner graphRunner;

    private void Start()
    {
        graphRunner.Run();
    }
}
