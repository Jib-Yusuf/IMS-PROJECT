Coverage: 60% QA Project Inventory Management System(IMS)

IMS project is a application that an end user can interact with the Command Line Interface.In this project I will have to create an application with using  supporting tools and methodologies that summarize all the fundamentals covered during this projects.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.


### Prerequisites

What things you need to install the software and how to install them

```
GIT
GitHub
Jira
MySQL
Java
Maven
JUnit
Mockito
```

### Installing

A step by step series of examples (Jar file) that tell you how to get a development env running

Say what the step will be

```
configured MySQL database
db.url=jdbc:mysql://localhost:3306/<your_db>
db.user=<username>
db.password=<password>

```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```


    @Test
    public void firstConstructorTEST() {
        Item item = new Item("Samsung",  799.99F);
        assertEquals("Samsung", item.getItemName());
        assertEquals(799.99, item.getPrice(), 0.02);
    }

     @Test
    public void setIdTEST() {
        Item item = new Item(1L, "Samsung", 799.99F);
        item.setId(2L);
        assertEquals(Long.valueOf("2"), item.getId());

    }

    @Test
    public void setItemNameTEST() {
        Item item = new Item(1L, "Samsung", 799.99F);
        item.setItemName("Samsung");
        assertEquals("Samsung", item.getItemName());
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc


