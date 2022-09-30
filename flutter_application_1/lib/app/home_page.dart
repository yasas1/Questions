import 'package:flutter/material.dart';
import 'package:flutter_application_1/app/login_page/login_page.dart';
import 'package:flutter_application_1/app/login_page/splash_page.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Home"),
      ),
      body: Container(
        child: Center(
          child: ElevatedButton(
            onPressed: (){
              Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => const SplashPage()));
            },
            child: Text("Log out"),
          ),
        ),
      ),
    );
  }
}
