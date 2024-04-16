using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AutoActivate : MonoBehaviour
{
    [Tooltip("The interval between activations in seconds.")]
    public float interval = 10f;

    private void Start()
    {
        InvokeRepeating(nameof(ActivateSelf), 0f, interval);
    }

    private void ActivateSelf() { gameObject.SendMessage("Activate"); }
}
