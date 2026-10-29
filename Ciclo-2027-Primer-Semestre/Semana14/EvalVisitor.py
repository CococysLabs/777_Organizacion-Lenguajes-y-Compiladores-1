from ExprVisitor import ExprVisitor
from ExprParser import ExprParser

class EvalVisitor(ExprVisitor):
    
    def visitAdd(self, ctx: ExprParser.AddContext):
        # Evaluar la suma
        left = self.visit(ctx.expr(0))  # Evaluar el primer operando
        right = self.visit(ctx.expr(1))  # Evaluar el segundo operando
        return left + right

    def visitMultiply(self, ctx: ExprParser.MultiplyContext):
        # Evaluar la multiplicación
        left = self.visit(ctx.term(0))  # Evaluar el primer factor
        right = self.visit(ctx.term(1))  # Evaluar el segundo factor
        return left * right

    def visitInt(self, ctx: ExprParser.IntContext):
        # Devolver el valor del número entero
        return int(ctx.INT().getText())