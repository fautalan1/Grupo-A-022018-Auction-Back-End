package appllication.annotation;

import appllication.entity.Auction;
import appllication.model.RequestPage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class LogAuditTime {

    static Logger logger = LoggerFactory.getLogger(LogAuditTime.class);
    /// ANNOTATION POINTCUT////
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTimeAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("/////// AROUND START  logExecutionTime annotation //////");
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        Object[] aArguments = joinPoint.getArgs();

        // Signature signature = joinPoint.getSignature();
        // logger.info(signature.getName());
        // logger.info(signature.getDeclaringType().toString());
        // logger.info(signature.getDeclaringTypeName());
        // logger.info(signature.toLongString());
        // logger.info(signature.toShortString());
        // logger.info(signature.getModifiers() + "");
        // logger.info(signature.toString());

            int numberArgument = 1;
            for(Object a : joinPoint.getArgs()){
                String argument = a.toString();
                int result    = argument.indexOf("RequestPage");
                int isAuction = argument.indexOf("appllication.entity.Auction");
                if(result != -1){
                    RequestPage aRequestPage = (RequestPage) a;
                    logger.info("The Arguments");
                    logger.info("email the user logeado: " + aRequestPage.getEmail());
                    logger.info("tile:" + aRequestPage.getTitle());
                    logger.info("description: " + aRequestPage.getDescription());
                    logger.info("index: " + aRequestPage.getIndex());
                    logger.info("size: " + aRequestPage.getSize());
                }
                if(isAuction != -1){
                    Auction anyAuction = (Auction) a;
                    logger.info("The Arguments");
                    logger.info("email the user logeado: " + anyAuction.getEmailAuthor());
                }

                logger.info("argument N° " + numberArgument + ": " + a.toString());
                numberArgument++;
            }




        logger.info("/////// " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
        logger.info("/////// AROUND FINISH  logExecutionTime annotation ///////");
        return proceed;
    }



}
