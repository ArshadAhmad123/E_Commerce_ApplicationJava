package com.example.bazaarwala.model;

public class Url {
    public static final String Main_Url = "http://192.168.29.226/practice/";
    public static final String Product_Url = Main_Url+"fetchProduct.php";
    public static final String Catagory_Url = Main_Url+"fetchCatagories.php";
    public static final String Search_Url = Main_Url+"search.php";
    public static final String SignUp_Url = Main_Url+"uploadUserData.php";
    public static final String Login_Url = Main_Url+"existUser.php";
}
//1. onCreate(): Activity banane ke baad yeh method call hota hai. Yeh method mein aap activity ke
// layout, views, aur resources ko initialize karte hain.
//        2. onStart(): Activity visible hone se pehle yeh method call hota hai.
//        Yeh method mein aap activity ke UI ko update karte hain.
//3. onResume(): Activity focus mein aane ke baad yeh method call hota hai. Yeh method mein
// aap activity ke UI ko update karte hain aur user interactions ko handle karte hain.
//        4. onPause(): Activity focus se bahar jaane ke baad yeh method call hota hai. Yeh
//        method mein aap activity ke resources ko release karte hain aur data ko save karte hain.
//5. onStop(): Activity visible hone se pehle yeh method call hota hai. Yeh method mein aap
// activity ke resources ko release karte hain aur data ko save karte hain.
//6. onDestroy(): Activity ko destroy karne se pehle yeh method call hota hai. Yeh method mein
// aap activity ke resources ko release karte hain aur data ko save karte hain.
//7. onRestart(): Activity ko restart karne se pehle yeh method call hota hai. Yeh method mein
// aap activity ke resources ko reinitialize karte hain.

//Fragments lifecycle
//1. onAttach(): Fragment ko activity se attach karne ke baad yeh method call hota hai.
//2. onCreate(): Fragment ko banane ke baad yeh method call hota hai.
//        3. onCreateView(): Fragment ko view banane ke baad yeh method call hota hai.
//4. onViewCreated(): Fragment ko view banane ke baad yeh method call hota hai.
//5. onStart(): Fragment ko start karne ke baad yeh method call hota hai.
//6. onResume(): Fragment ko resume karne ke baad yeh method call hota hai.
//7. onPause(): Fragment ko pause karne ke baad yeh method call hota hai.
//8. onStop(): Fragment ko stop karne ke baad yeh method call hota hai.
//9. onDestroyView(): Fragment ko destroy karne ke baad yeh method call hota hai.
//10. onDestroy(): Fragment ko destroy karne ke baad yeh method call hota hai.
//11. onDetach(): Fragment ko detach karne ke baad yeh method call hota hai.

//retrofit api http
//Retrofit ek HTTP client hai jo HTTP aur HTTPS protocols par kaam karta hai.
// Yeh network protocols ka use karke server se data ko fetch karta hai aur server ko data ko
// send karta hai.
//Retrofit API ki latest version 2.11.0 hai ¹ ². Yeh version March 28, 2024 ko release hui thi.
//1. HTTP Request: Web browser HTTP request ko web server ko bhejta hai. Request mein URL, method (GET, POST, PUT, DELETE, etc.), headers, aur body hota hai.
//2. HTTP Response: Web server HTTP response ko web browser ko bhejta hai. Response mein status code, headers, aur body hota hai.
//3. HTTP Methods: HTTP mein kuchh basic methods hain:
//- GET: Data ko fetch karna
//- POST: Data ko create karna
//- PUT: Data ko update karna
//- DELETE: Data ko delete karna
//1. HTTP Status Codes: HTTP status codes response ki status ko darshate hain. Kuchh common status codes hain:
//- 200 OK: Request successful
//- 404 Not Found: Resource not found
//- 500 Internal Server Error: Server error