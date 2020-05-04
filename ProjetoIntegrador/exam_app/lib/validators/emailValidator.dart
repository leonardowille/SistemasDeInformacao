import 'package:examapp/validators/notEmptyValidator.dart';
import 'package:examapp/validators/validator.dart';

class EmailValidator implements Validator {
  @override
  String validate(String value) {
     String validate = NotEmptyValidator().validate(value);
    // TODO: build validation
    return validate;
  }
}
