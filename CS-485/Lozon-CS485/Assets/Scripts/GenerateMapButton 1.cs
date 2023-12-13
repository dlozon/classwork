using InsaneScatterbrain.ScriptGraph;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GenerateMapButton : MonoBehaviour
{
    [Tooltip("Toggles button functionality")]
    public bool active;

    [SerializeField] private ScriptGraphRunner graphRunner;

    private void Activate()
    {
        // TODO: animate button

        if (active)
            graphRunner.Run();
    }
}
