import 'package:flutter/material.dart';
import 'package:flutter_application_1/app/custon_widget/custom_material_button.dart';

class SignInButton extends CustomMaterialButton {
  SignInButton({
    super.key,
    required String text,
    required Color color,
    required Color textColor,
    double fontSize = 20,
    required double borderRadius,
    required VoidCallback onPressed,
  }) : super(
            color: color,
            borderRadius: borderRadius,
            onPressed: onPressed,
            child: Text(
              text,
              style: TextStyle(
                color: textColor,
                fontSize: fontSize,
              ),
            ));
}
