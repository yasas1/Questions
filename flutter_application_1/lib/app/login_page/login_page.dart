import 'package:flutter/material.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController _usernameController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Login Page"),
        leading: const Icon(
          Icons.cabin,
          color: Colors.white,
        ),
      ),
      body: Container(
          child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Container(
              padding: const EdgeInsets.only(top: 5),
              child: const Text(
                "Login",
                style: TextStyle(
                  color: Colors.blue,
                  fontSize: 30,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            Container(
              height: 200,
              width: 400,
              child: Image.asset("assets/images/flutter.png"),
            ),
            Container(
              padding: EdgeInsets.symmetric(horizontal: 10),
              child: Column(
                children: [
                  //-------username
                  Container(
                    padding: const EdgeInsets.only(bottom: 30),
                    child: TextFormField(
                      controller: _usernameController,
                      decoration: const InputDecoration(
                          border: UnderlineInputBorder(),
                          labelText: "Enter your username",
                          hintText: "Username"),
                    ),
                  ),
                  //-----passsowrd
                  Container(
                    padding: const EdgeInsets.only(bottom: 30),
                    child: TextFormField(
                      controller: _passwordController,
                      decoration: const InputDecoration(
                          border: UnderlineInputBorder(),
                          labelText: "Enter your password",
                          hintText: "Password"),
                    ),
                  ),
                  //----login button
                  Container(
                    padding: EdgeInsets.symmetric(vertical: 30),
                    width: 200,
                    child: ElevatedButton(
                      onPressed: () {
                        print("Login button clicked");
                      },
                      child: const Text(
                        'Login',
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 20,
                        ),
                      ),
                    ),
                  )
                ],
              ),
            )
          ],
        ),
      )),
    );
  }
}

// Container(
// padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 15),
// //in-side
// margin: const EdgeInsets.symmetric(horizontal: 10, vertical: 15),
// // out-side
// decoration: BoxDecoration(
// color: Colors.orange,
// borderRadius: BorderRadius.circular(10),
// boxShadow: [
// BoxShadow(
// color: Colors.grey.withOpacity(0.4),
// blurRadius: 2.0,
// spreadRadius: 3.0
// )
// ]),
// width: 200,
// height: 100,
// child: Center(
// child: Image.asset("assets/images/flutter.png")
// ),
// )
