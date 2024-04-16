using System.Collections;
using UnityEngine;

public class GoInCircles : MonoBehaviour
{
    [Tooltip("Speed of rotation in degrees per second")]
    public float rotationSpeed = 30.0f;
    [Tooltip("Radius of the circular path")]
    public float radius = 5.0f;

    private Vector3 initialPosition;
    private float angle = 0.0f;

    private void Start()
    {
        initialPosition = transform.position;
    }

    private void Update()
    {
        // Calculate the new position in the circular path
        float x = initialPosition.x + radius * Mathf.Cos(Mathf.Deg2Rad * angle);
        float z = initialPosition.z + radius * Mathf.Sin(Mathf.Deg2Rad * angle);

        // Update the game object's position
        transform.position = new Vector3(x, initialPosition.y, z);

        // Increment the angle to make the object move in a circle
        angle += rotationSpeed * Time.deltaTime;
        angle %= 360;
    }
}