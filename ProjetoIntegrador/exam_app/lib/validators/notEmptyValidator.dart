import 'package:examapp/validators/validator.dart';

class NotEmptyValidator extends Validator {
  @override
  String validate(String value) {
    if (value.isEmpty) {
      return 'Campo obrigatório.';
    }
    return null;
  }
}
