import 'package:flutter/material.dart';
import 'package:flutter_application_1/app/login_page/login_page.dart';

class SplashPage extends StatefulWidget {
  const SplashPage({Key? key}) : super(key: key);

  @override
  State<SplashPage> createState() => _SplashPageState();
}

class _SplashPageState extends State<SplashPage> {
  @override
  void initState() {
    super.initState();
    _navigateToLoginScreen(context);
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: double.infinity,
      color: Colors.white,
      child: Center(
        child: Image.asset("assets/images/flutter.png"),
      ),
    );
  }

  void _navigateToLoginScreen(BuildContext context) {
    Future.delayed(Duration(seconds: 2), (){
      // push replacement to avoid go back to previous page from back button
      Navigator.pushReplacement(context, MaterialPageRoute(builder: (context) => const LoginPage()));
    });
  }
}
