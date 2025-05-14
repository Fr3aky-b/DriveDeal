package com.francis.drivedeal.navigation

const val ROUT_HOME = "home"
const val ROUT_ABOUT = "about"
const val ROUT_REGISTER = "Register"
const val ROUT_LOGIN = "Login"

const val ROUT_SPLASH = "splash"
const val ROUT_USER_DASHBOARD = "userdashboard"
const val ROUT_CAR_DASHBOARD = "cardashboard"
const val ROUT_BENTLEY = "bentley"
const val ROUT_BMW = "bima"
const val ROUT_LAMBO = "lambo"
const val ROUT_MERCEDES = "benz"
const val ROUT_ROLLS = "rolls"


//Products

const val ROUT_ADD_PRODUCT = "add_product"
const val ROUT_PRODUCT_LIST = "product_list"
const val ROUT_ADMIN_PRODUCT_LIST = "product_list"
const val ROUT_EDIT_PRODUCT = "edit_product/{productId}"

// ✅ Helper function for navigation
fun editProductRoute(productId: Int) = "edit_product/$productId"