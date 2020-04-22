import 'package:examapp/validators/validator.dart';
import 'package:flutter/material.dart';

class InputComponent extends StatelessWidget {
  String hintText;
  Validator validator;
  TextInputType keyboardType;
  TextEditingController controller;
  bool obscureText;

  InputComponent(
      {this.hintText, this.validator, this.keyboardType, this.controller, this.obscureText});

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      decoration: InputDecoration(
        hintText: hintText,
        contentPadding: EdgeInsets.all(12),
      ),
      validator: validator.validate,
      maxLength: 50,
      keyboardType: keyboardType == null ? TextInputType.text : keyboardType,
      textCapitalization: TextCapitalization.words,
      controller: controller,
      obscureText: obscureText == null ? false : obscureText,
    );
  }
}
