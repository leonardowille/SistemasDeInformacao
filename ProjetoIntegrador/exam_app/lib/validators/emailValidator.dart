import 'package:examapp/validators/notEmptyValidator.dart';
import 'package:examapp/validators/validator.dart';

class EmailValidator implements Validator {
  static final RegExp EMAIL_REGEX = new RegExp(
      r'^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$');

  @override
  String validate(String value) {
    String validate = NotEmptyValidator().validate(value);
    if (validate == null && !EMAIL_REGEX.hasMatch(value)) {
      validate = "E-mail inválido";
    }
    return validate;
  }
}
