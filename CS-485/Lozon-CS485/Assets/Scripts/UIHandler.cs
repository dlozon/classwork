using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class UIHandler : MonoBehaviour
{
    private Slider healthBar;
    private Slider staminaBar;
    private GameObject player;
    private PlayerController playerController;
    private float delay = 1;
    private bool started;

    // Update is called once per frame
    void Update()
    {
        delay -= Time.deltaTime;
        if (delay <= 0 && !started)
            delayedStart();

        if (started)
        {
            healthBar.value = playerController.HP;
            staminaBar.value = playerController.stamina;
        }
    }

    void delayedStart()
    {
        player = GameObject.FindWithTag("Player");
        playerController = player.GetComponent<PlayerController>();
        healthBar = GameObject.Find("/UI/Health Bar").GetComponent<Slider>();
        staminaBar = GameObject.Find("/UI/Stamina Bar").GetComponent<Slider>();

        healthBar.value = healthBar.maxValue = playerController.maxHP;
        staminaBar.value = staminaBar.maxValue = playerController.maxSprintTime;

        started = true;
    }
}
