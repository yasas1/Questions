import 'package:flutter/material.dart';

import '../custon_widget/custom_material_button.dart';
import '../custon_widget/icon_sign_in_button.dart';
import '../custon_widget/sing_in_button.dart';

class SignInPage extends StatelessWidget {
  const SignInPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Learn Tracker'),
        centerTitle: true,
        elevation: 4.0,
        leading: const Icon(
          Icons.abc_rounded,
          size: 40,
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.favorite_border),
            onPressed: () {},
          ),
          const Icon(
            Icons.notification_add,
          ),
        ],
      ),
      body: _signContent(),
      backgroundColor: Colors.grey[200],
    );
  }

  Widget _signContent() {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          const Text(
            'Sign In',
            textAlign: TextAlign.center,
            style: TextStyle(fontSize: 32, fontWeight: FontWeight.w600),
          ),
          const SizedBox(height: 8.0),
          SignInButton(
            text: 'sign with email',
            color: Colors.black,
            textColor: Colors.white,
            borderRadius: 16.0,
            onPressed: () {
              print('sign in button');
            },
          ),
          const SizedBox(height: 8.0),
          IconSignInButton(
            iconData: Icons.email_rounded,
            text: 'sign with email',
            color: Colors.white,
            textColor: Colors.black,
            borderRadius: 16.0,
            onPressed: () {
              print('sign in button');
            },
          ),
        ],
      ),
    );
  }
}
