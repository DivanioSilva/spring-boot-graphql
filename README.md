spring-boot-graphql

#Spring boot graphql endpoint:
http://localhost:8080/graphiql

#Examples of queries:

##findAllCustomersAndOrders
{
    customers {
            id
            name
                orders {
                id
            }
    }
}

##createCustomer
mutation createCustomer{
    createCustomer(name:"Alice") {
        id
        name
    }
}
33createOrder

##createOrder
mutation createOrder{
    createOrder(inputOrder: {
        id: 99
        customerId: 1
    }){
        id
        customerId
    }
}

##updateCustomer
mutation{
    updateCustomer(customerId: 1, customerName:"Divanio Silva") {
        id
        name
    }
}

##deleteCustomerByName
mutation{
    deleteCustomerByName(customerName: "Alice")
}

##deleteCustomerById
mutation{
    deleteCustomerById(customerId: 5)
}
