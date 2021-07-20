### Notes from Requirement doc

1. The fare varies based on the time of the trip. There are two types of fares based on time of travel:
    - Peak hours timings
        - Monday - Friday
            - 07:00 - 10:30, 17:00 - 20:00
        - Saturday - Sunday
            - 09:00 - 11:00, 18:00 - 22:00
    - Off-peak hours timings
    - All hours except the above peak hours

2. Fare Rules

Zones | Peak hour charge | off peak hour charge
------|------------------|---------------------
1-1   | 30               |25
1-2   | 35               |30
2-2   | 25               |20

3. Fare limits

Zones | Daily limit      | Weekly limit
------|------------------|---------------------
1-1   | 100              | 500
1-2   | 120              | 600
2-2   | 80               | 400


### Known limitations
1. Doesn't support computation per event
2. As no unique commuter ID present can not fail fast for fare limits
3. A mix of Solitary and Sociable tests
4. Predicting an extension of zone (Explained in the problem statement)
5. Predicting an addition of monthly are limit (explained in the example)