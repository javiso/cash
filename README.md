# cash - Instructions 

APIS:
In this section, you will find examples of how to send the requests as well as the possible responses obtained. 
Note: The data are already stored in the app(H2 database). These are loaded during the run process of the app. 

USER'S ENPOINTS

- Get: cash/v1/user/{idUser}
Description: It gets data and loans (if any) for a specific user.
Example: cash/v1/user/1
Result: 
{

    "id": 1,
    
    "firstName": "juan",
    "lastName": "pepe",
    "email": "juanpepe@gmail.com",
    "loans": [
        {
            "id": 3,
            "total": 2.60,
            "idUser": 1
        },
        {
            "id": 2,
            "total": 6.80,
            "idUser": 1
        },
        {
            "id": 1,
            "total": 4.50,
            "idUser": 1
        }
    ]
}

- Post: /cash/v1/user
Description: It creates a specific user.
Example: /cash/v1/user
Body: 
{
    "email": "mickeyMouse@gmail.com",
    "firstName": "mickey",
    "lastName":"mouse"
}
Result:
{
    "id": 5,
    "firstName": "mickey",
    "lastName": "mouse",
    "email": "mickeyMouse@gmail.com",
    "loans": []
}

- Delete: cash/v1/user/{idUser}
Description: It deletes a specific user
Example: cash/v1/user/5
Result: Response's body: Empty. Status: 200 Ok

LOAN'S ENDPOINTS
Description: 
- Get: /cash/v1/loan
Parameters: page, size, id_user (By default: size=5, page = 0)
Description: It gets all the loans for a specific user. You can configure the result by size, page and id_user. id_user is optional. If you don't add it in the search, this endpoint will fetch the loans for all the existing users.
Example: /cash/v1/loan
Parameters: page = 1, size= 2, id_user=1
Result:
{
    "items": [
        {
            "id": 3,
            "total": 2.60,
            "idUser": 1
        }
    ],
    "paging": {
        "page": 1,
        "size": 2,
        "total": 3
    }
}
