using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class VisionCone : MonoBehaviour
{
    private GameObject player;

    [HideInInspector]
    public bool seesPlayer;

    // Start is called before the first frame update
    void Start()
    {
        player = GameObject.FindWithTag("Player");
    }

    private void OnTriggerStay(Collider other)
    {
        if(other.gameObject.Equals(player))
            seesPlayer = true;
    }
}
