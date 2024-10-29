# Hibernate Validator

    Hibernate Validator는 Bean Validation 명세의 구현체입니다.
    도메인 모델에서 어노테이션을 통한 필드값 검증을 가능하게 도와줍니다.

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
        <version>3.3.3</version>
    </dependency>

# Hibernate Validator 어노테이션

    @Null
    @NotNull
    @NotEmpty
    @NotBlank

    @DecimalMax
    @DecimalMin
    @Min
    @Max

    @Positive
    @PositiveOrZero
    @Negative
    @NegativeOrZero

    @Future
    @FutureOrPresent
    @Past
    @PastOrPresent

    @email
    @Digits

    @AssertTrue
    @AssertFalse

    @Size
    @Pattern

# Hibernate Validator 적용

    자바 기반
    @Valid @RequestBody

    스프링 기반, @Valid를 포함, 그룹화 기능
    @Validated @RequestBody
    @Validated(ValidatedGroupName.class) @RequestBody

# 사용자 정의 Hibernate Validator 어노테이션

    1. 단계: ConstraintValidator 구현
    public class TelephoneValidator implements ConstraintValidator<Telephone, String> {
        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null) {
            return false;
        }
            return s.matches("\"01(?:0|1|[6-9])[.-]?(\\\\d{3}|\\\\d{4})[.-]?(\\\\d{4})$\"");
        }
    }

    2. 단계: 어노테이션 인테페이스 선언
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = TelephoneValidator.class)
    public @interface Telephone {
        String message() default "전화번호 형식이 일치하지 않습니다.";
        Class[] groups() default {};
        Class[] payload() default {};
    }

    3. 단계: 호출
    @Telephone

# 스프링 부트 예외 처리

    1. 단계: 사용자 정의 스프링 부트 예외 처리 클래스 생성
    @Slf4j
    @RestControllerAdvice
    public class CustomExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> handlerException(
        RuntimeException e,
        HttpServletRequest request
    ) {
            HttpHeaders responseHeaders = new HttpHeaders();
            HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    
            log.error("Advice 내 httpException 호출, {}, {}", request.getRequestURI(), e.getMessage());
    
            Map<String, String> map = new HashMap<String, String>();
            map.put("error type", httpStatus.getReasonPhrase());
            map.put("code", "400");
            map.put("message", e.getMessage());
    
            return new ResponseEntity<>(map, responseHeaders, httpStatus);
        }
    }

    2. 호출
    throw new RuntimeException("customException 메소드 호출");

# 사용자 정의 예외 처리

    1. 단계: 사용자 정의 예외 클래스 열거형 생성
    public class Constants {
        public enum ExceptionClass {
        }
    }

    2. 단계: 사용자 정의 예외 클래스 생성
    ublic class CustomException extends Exception { }

    3. 단계: 사용자 정의 예외 클래스 선언
    @ExceptionHandler(value = CustomException.class)

    4. 호출
     public void customException() throws CustomException {
        throw new CustomException();
    }



