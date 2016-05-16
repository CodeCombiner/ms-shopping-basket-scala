# Actor-based Shopping Basket REST API written in Scala, Akka and Play

```config
# Installation
Java 8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
Scala 2.11.8 (http://www.scala-lang.org/download/)
SBT 0.13.11 (http://www.scala-sbt.org/download.html)
git clone https://github.com/cristinanegrean/ms-shopping-basket-scala.git
cd ms-shopping-basket-scala
sbt run
```

# endpoints

```config
# Product
GET         /products                                       shoppingbasket.controllers.ProductController.products(page: Int ?= 1, pageSize: Int ?= 50, available: Option[Boolean])
GET         /products/:id                                   shoppingbasket.controllers.ProductController.product(id: String)

# Shopping Basket
POST        /shoppingbaskets                                shoppingbasket.controllers.BasketController.post()
GET         /shoppingbaskets/:id                            shoppingbasket.controllers.BasketController.get(id: String)

# Shopping Basket Item Operations
GET         /shoppingbaskets/:basketId/items/:itemId        shoppingbasket.controllers.BasketItemController.itemByBasket(basketId: String, itemId: String)
DELETE      /shoppingbaskets/:basketId/items/:itemId        shoppingbasket.controllers.BasketItemController.deleteItemFromBasket(basketId: String, itemId: String)
POST        /shoppingbaskets/:basketId/items                shoppingbasket.controllers.BasketItemController.addItemToBasket(basketId)
```


# description

* GET /api/products/products?page,pageSize,available
    * (x) paginated collection of products (x)
    * (x) query parameters `available=true` - to display only those products which are still available
    
```javascript
{
  "pageInfo": {
    "page": 1,
    "pageSize": 2,
    "total": 31
  },
  "data": [
    {
      "id": "86def8b0-e2cb-4c6d-9a3e-e2b50936ed15",
      "name": "LBegK905LA",
      "description": "BljrwTK4CRu02Rb15y9IayNEJi38a1viwtrFnK8H4JYVQ8qlB6",
      "price": 27,
      "currency": "EUR",
      "stock": 2,
      "available": true,
      "link": {
        "rel": "self",
        "href": "http://localhost:9000/api/products/86def8b0-e2cb-4c6d-9a3e-e2b50936ed15"
      }
    },
    {
      "id": "66a23514-3566-4d04-909e-043b542dc475",
      "name": "yz2mLZ979l",
      "description": "Oek8LbydDirgIVX6Df3a1QnPjDLdjS04YVSStLX44EL4Y0wK1C",
      "price": 29,
      "currency": "EUR",
      "stock": 9,
      "available": true,
      "link": {
        "rel": "self",
        "href": "http://localhost:9000/api/products/66a23514-3566-4d04-909e-043b542dc475"
      }
    }
  ]
}
```

* GET /api/products/:id
    * product by id
```javascript
{
  "id": "86def8b0-e2cb-4c6d-9a3e-e2b50936ed15",
  "name": "LBegK905LA",
  "description": "BljrwTK4CRu02Rb15y9IayNEJi38a1viwtrFnK8H4JYVQ8qlB6",
  "price": 27,
  "currency": "EUR",
  "stock": 2,
  "available": true,
  "link": {
    "rel": "self",
    "href": "http://localhost:9000/api/products/86def8b0-e2cb-4c6d-9a3e-e2b50936ed15"
  }
}
```

* POST /api/shoppingbaskets
    * (x) creates a shopping basket
        * (x)  a new request to this endpoint creates a new basket
    * (x)  Returns 201 + Location Header with the location of the resource
    * (!) a cart can be in multiple statuses  <- future enhancements
            
```javascript
{
    "items": [
        {
            "product": {
                "id": "86def8b0-e2cb-4c6d-9a3e-e2b50936ed15"
            },
            "capacity": 2
        },
        {
            "product": {
                "id": "66a23514-3566-4d04-909e-043b542dc475"
            },
            "capacity": 9
        }
    ]
}
```


* GET /api/shoppingbaskets/:id
    * shows the content of the
    
```javascript
{
  "id": "c763860b-636b-4cc1-9ed9-998b4ea2fcac",
  "items": [
    {
      "id": "d053aead-9b87-492f-b505-5537ad7f1633",
      "product": {
        "id": "86def8b0-e2cb-4c6d-9a3e-e2b50936ed15",
        "name": "LBegK905LA",
        "description": "BljrwTK4CRu02Rb15y9IayNEJi38a1viwtrFnK8H4JYVQ8qlB6",
        "price": 27,
        "currency": "EUR",
        "stock": 2,
        "link": {
          "rel": "self",
          "href": "http://localhost:9000/api/products/86def8b0-e2cb-4c6d-9a3e-e2b50936ed15"
        }
      },
      "addedDt": 1463436265176,
      "capacity": 2,
      "link": {
        "rel": "self",
        "href": "http://localhost:9000/api/shoppingbaskets/c763860b-636b-4cc1-9ed9-998b4ea2fcac/items/d053aead-9b87-492f-b505-5537ad7f1633"
      }
    },
    {
      "id": "829dab72-07cf-4e51-8b90-57c9b8d474a7",
      "product": {
        "id": "66a23514-3566-4d04-909e-043b542dc475",
        "name": "yz2mLZ979l",
        "description": "Oek8LbydDirgIVX6Df3a1QnPjDLdjS04YVSStLX44EL4Y0wK1C",
        "price": 29,
        "currency": "EUR",
        "stock": 9,
        "link": {
          "rel": "self",
          "href": "http://localhost:9000/api/products/66a23514-3566-4d04-909e-043b542dc475"
        }
      },
      "addedDt": 1463436265176,
      "capacity": 9,
      "link": {
        "rel": "self",
        "href": "http://localhost:9000/api/shoppingbaskets/c763860b-636b-4cc1-9ed9-998b4ea2fcac/items/829dab72-07cf-4e51-8b90-57c9b8d474a7"
      }
    }
  ],
  "link": {
    "rel": "self",
    "href": "http://localhost:9000/api/shoppingbaskets/c763860b-636b-4cc1-9ed9-998b4ea2fcac"
  }
}
```

* `POST /api/shoppingbaskets/c763860b-636b-4cc1-9ed9-998b4ea2fcac/items`
    * adds a new element to the basket
    
```javascript
{
    "product": {
        "id": "e87b5f02-1676-4b3c-9216-98f3d514c2e5"
    },
    "capacity": 1
}
```

     * After the POST, the new element will be visible in `GET /api/shoppingbaskets/c763860b-636b-4cc1-9ed9-998b4ea2fcac`
     
    
* GET /api/shoppingbaskets/c763860b-636b-4cc1-9ed9-998b4ea2fcac/items/ecd2f5ca-da23-4b63-a1b4-22b23b0a115a

```javascript
{
  "id": "ecd2f5ca-da23-4b63-a1b4-22b23b0a115a",
  "product": {
    "id": "f624f049-8283-42db-8b39-d45553444845",
    "name": "sbKEIPOYHw",
    "description": "7Yvoa4hsngn5PtP1c25d8WZfpdH8QCKWX7SkjbIwiDZFWIUvUb",
    "price": 77,
    "currency": "EUR",
    "link": {
      "rel": "self",
      "href": "http://localhost:9000/products/f624f049-8283-42db-8b39-d45553444845"
    }
  },
  "addedDt": 1456088336892,
  "capacity": 1,
  "link": {
    "rel": "self",
    "href": "http://localhost:9000/shoppingbaskets/436f0c1b-5878-49a4-ac01-223de24058bd/items/ecd2f5ca-da23-4b63-a1b4-22b23b0a115a"
  }
}
```

* DELETE /api/shoppingbaskets/d83jjdf939/items/d83jjdaffd
    * Deletes the given item from the basket

* DELETE /shoppingbaskets/:id (!) not implemented
