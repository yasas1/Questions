import 'package:flutter/material.dart';
import 'package:flutter_application_1/app/custon_widget/custom_material_button.dart';

class IconSignInButton extends CustomMaterialButton {
  IconSignInButton({
    super.key,
    IconData iconData = Icons.email_outlined,
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
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Icon(
                  iconData,
                  size: 40,
                ),
                Text(
                  text,
                  style: TextStyle(
                    color: textColor,
                    fontSize: fontSize,
                  ),
                ),
                Opacity(
                  opacity: 0.0,
                  child: Icon(
                    iconData,
                    size: 40,
                  ),
                )
              ],
            ));
}
