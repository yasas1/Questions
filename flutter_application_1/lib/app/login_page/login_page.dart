import 'package:flutter/material.dart';
import 'package:flutter_application_1/app/home_page.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController _usernameController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();

  @override
  void initState() {
    super.initState();
    print("init function");
  }

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
              // --------- login text
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
              // -------- login Logo
              height: 200,
              width: 400,
              child: Image.asset("assets/images/flutter.png"),
            ),
            Container(
              padding: EdgeInsets.symmetric(horizontal: 10),
              child: Column(
                children: [
                  Container(
                    // ------- username
                    padding: const EdgeInsets.only(bottom: 30),
                    child: TextFormField(
                      controller: _usernameController,
                      decoration: const InputDecoration(
                          border: UnderlineInputBorder(),
                          labelText: "Enter your username",
                          hintText: "Username"),
                      onChanged: (value) {
                        print("value : $value");
                      },
                    ),
                  ),
                  Container(
                    // ----- passsword
                    padding: const EdgeInsets.only(bottom: 30),
                    child: TextFormField(
                      controller: _passwordController,
                      decoration: const InputDecoration(
                          border: UnderlineInputBorder(),
                          labelText: "Enter your password",
                          hintText: "Password"),
                    ),
                  ),
                  Container(
                    //  ------ login button
                    padding: EdgeInsets.symmetric(vertical: 30),
                    width: 200,
                    child: ElevatedButton(
                      onPressed: _loginFunction,
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

  void _loginFunction() {
    if (_usernameController.text == "yasas" &&
        _passwordController.text == "12345") {
      print("Logged In");
      Navigator.pushReplacement(
          context, MaterialPageRoute(builder: (context) => HomePage(username: _usernameController.text)));
    } else {
      print("Loging Failed");
      _showAlertDialog(context);
    }
  }

  void _showAlertDialog(BuildContext context) {
    Widget okButton = ElevatedButton(
      child: Text("Ok"),
      onPressed: () {
        Navigator.pop(context);
      },
    );
    AlertDialog alertDialog = AlertDialog(
      title: const Text("Login Error"),
      content: const Text("Invalid Credentials"),
      actions: [okButton],
    );

    showDialog(
        context: context,
        builder: (BuildContext context) {
          return alertDialog;
        });
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
