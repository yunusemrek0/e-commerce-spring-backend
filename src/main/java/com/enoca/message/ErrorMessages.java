package com.enoca.message;

public class ErrorMessages {




    private ErrorMessages(){
    }

    public static final String ALREADY_REGISTER_MESSAGE_USERNAME = "Error: User with username %s is already registered";

    public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Error: User with email %s is already registered";

    public static final String NOT_FOUND_PRODUCT_MESSAGE = "Error: Product not found with name %s";

    public static final String NOT_FOUND_PRODUCT_ID_MESSAGE = "Error: Product not found with id %d";

    public static final String ALREADY_CREATED_PRODUCT_NAME_MESSAGE = "Error: Product with name %s is already exist";

    public static final String NOT_ENOUGH_PRODUCT_QUANTITY = "Error: Product quantity is not enough for sale.";





    public static final String NOT_FOUND_CUSTOMER_ID = "Error: Customer cannot found with id %d";

    public static final String NOT_FOUND_CART_ID ="Error: Cart cannot found with id %d" ;


    public static final String NOT_FOUND_ORDER_CODE = "Error: Order cannot found with code %s";
}
