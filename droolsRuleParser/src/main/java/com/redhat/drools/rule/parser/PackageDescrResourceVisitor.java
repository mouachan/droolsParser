package com.redhat.drools.rule.parser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.drools.compiler.compiler.DrlExprParser;
import org.drools.compiler.lang.api.CEDescrBuilder;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.api.FunctionDescrBuilder;
import org.drools.compiler.lang.api.PackageDescrBuilder;
import org.drools.compiler.lang.api.PatternDescrBuilder;
import org.drools.compiler.lang.api.RuleDescrBuilder;
import org.drools.compiler.lang.api.TypeDeclarationDescrBuilder;
import org.drools.compiler.lang.descr.AccessorDescr;
import org.drools.compiler.lang.descr.AccumulateDescr;
import org.drools.compiler.lang.descr.AccumulateImportDescr;
import org.drools.compiler.lang.descr.ActionDescr;
import org.drools.compiler.lang.descr.AndDescr;
import org.drools.compiler.lang.descr.AnnotatedBaseDescr;
import org.drools.compiler.lang.descr.AnnotationDescr;
import org.drools.compiler.lang.descr.AtomicExprDescr;
import org.drools.compiler.lang.descr.AttributeDescr;
import org.drools.compiler.lang.descr.BaseDescr;
import org.drools.compiler.lang.descr.BehaviorDescr;
import org.drools.compiler.lang.descr.BindingDescr;
import org.drools.compiler.lang.descr.CollectDescr;
import org.drools.compiler.lang.descr.ConstraintConnectiveDescr;
import org.drools.compiler.lang.descr.DeclarativeInvokerDescr;
import org.drools.compiler.lang.descr.EntryPointDeclarationDescr;
import org.drools.compiler.lang.descr.EnumDeclarationDescr;
import org.drools.compiler.lang.descr.EvalDescr;
import org.drools.compiler.lang.descr.ExistsDescr;
import org.drools.compiler.lang.descr.ExprConstraintDescr;
import org.drools.compiler.lang.descr.FactTemplateDescr;
import org.drools.compiler.lang.descr.FieldConstraintDescr;
import org.drools.compiler.lang.descr.FieldTemplateDescr;
import org.drools.compiler.lang.descr.ForallDescr;
import org.drools.compiler.lang.descr.FromDescr;
import org.drools.compiler.lang.descr.FunctionDescr;
import org.drools.compiler.lang.descr.FunctionImportDescr;
import org.drools.compiler.lang.descr.GlobalDescr;
import org.drools.compiler.lang.descr.ImportDescr;
import org.drools.compiler.lang.descr.LiteralRestrictionDescr;
import org.drools.compiler.lang.descr.MVELExprDescr;
import org.drools.compiler.lang.descr.NamedConsequenceDescr;
import org.drools.compiler.lang.descr.NotDescr;
import org.drools.compiler.lang.descr.OrDescr;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.compiler.lang.descr.PatternDescr;
import org.drools.compiler.lang.descr.PredicateDescr;
import org.drools.compiler.lang.descr.QueryDescr;
import org.drools.compiler.lang.descr.RelationalExprDescr;
import org.drools.compiler.lang.descr.RuleDescr;
import org.drools.compiler.lang.descr.TypeDeclarationDescr;
import org.drools.compiler.lang.descr.TypeFieldDescr;
import org.drools.compiler.lang.descr.WindowDeclarationDescr;
import org.drools.template.parser.DefaultTemplateContainer;
import org.drools.template.parser.RuleTemplate;
import org.drools.template.parser.TemplateContainer;
import org.kie.internal.builder.conf.LanguageLevelOption;

import com.redhat.drools.rule.model.Consequence;
import com.redhat.drools.rule.model.Constraint;
import com.redhat.drools.rule.model.Expression;
import com.redhat.drools.rule.model.Function;
import com.redhat.drools.rule.model.Node;
import com.redhat.drools.rule.model.Parameter;
import com.redhat.drools.rule.model.Rule;
import com.redhat.drools.rule.model.RuleAttribute;
import com.redhat.drools.rule.model.RuleSet;
import com.redhat.drools.rule.model.TypeDeclaration;
import com.redhat.drools.rule.model.Node.Gate;

/**
 * Visitor of package descriptor
 * 
 * @author mouachan
 *
 */
public class PackageDescrResourceVisitor {

	private static final Logger logger = Logger.getLogger(PackageDescrResourceVisitor.class);
	private DrlExprParser drlexpparser = new DrlExprParser(LanguageLevelOption.DRL6);

	private Expression expression = null;
	private Constraint constraint = null;
	private RuleSet ruleset = new RuleSet();

	private void checkResource(BaseDescr descr) {
		logger.info(descr.getClass().getSimpleName());
		logger.debug(descr.toString());
		if (descr != null) {
			assertNotNull(descr.getClass().getSimpleName() + ".resource is null!", descr.getResource());
		}
	}

	/**
	 * Visit descriptor (recursive call depending on descriptor type)
	 * 
	 * @param descr
	 */
	public void visit(final Object descr) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		String lastMethodName = null;
		for (int i = 0; i < 5; ++i) {
			String thisMethodName = stack[i].getMethodName() + ":" + stack[i].getLineNumber();
			if (thisMethodName.equals(lastMethodName)) {
				fail("Infinite loop detected!");
			}
			lastMethodName = thisMethodName;
		}
		if (descr instanceof AccessorDescr) {
			visit((AccessorDescr) descr);
		} else if (descr instanceof AccumulateDescr) {
			visit((AccumulateDescr) descr);
		} else if (descr instanceof ActionDescr) {
			visit((ActionDescr) descr);
		} else if (descr instanceof AndDescr) {
			visit((AndDescr) descr);
		} else if (descr instanceof AnnotationDescr) {
			visit((AnnotationDescr) descr);
		} else if (descr instanceof AtomicExprDescr) {
			visit((AtomicExprDescr) descr);
		} else if (descr instanceof AttributeDescr) {
			visit((AttributeDescr) descr);
		} else if (descr instanceof BindingDescr) {
			visit((BindingDescr) descr);
		} else if (descr instanceof CollectDescr) {
			visit((CollectDescr) descr);
		} else if (descr instanceof ConstraintConnectiveDescr) {
			visit((ConstraintConnectiveDescr) descr);
		} else if (descr instanceof ExistsDescr) {
			visit((ExistsDescr) descr);
		} else if (descr instanceof ExprConstraintDescr) {
			visit((ExprConstraintDescr) descr);
		} else if (descr instanceof FactTemplateDescr) {
			visit((FactTemplateDescr) descr);
		} else if (descr instanceof FieldConstraintDescr) {
			visit((FieldConstraintDescr) descr);
		} else if (descr instanceof FieldTemplateDescr) {
			visit((FieldTemplateDescr) descr);
		} else if (descr instanceof ForallDescr) {
			visit((ForallDescr) descr);
		} else if (descr instanceof FromDescr) {
			visit((FromDescr) descr);
		} else if (descr instanceof FunctionDescr) {
			visit((FunctionDescr) descr);
		} else if (descr instanceof FunctionImportDescr) {
			visit((FunctionImportDescr) descr);
		} else if (descr instanceof GlobalDescr) {
			visit((GlobalDescr) descr);
		} else if (descr instanceof ImportDescr) {
			visit((ImportDescr) descr);
		} else if (descr instanceof LiteralRestrictionDescr) {
			visit((LiteralRestrictionDescr) descr);
		} else if (descr instanceof MVELExprDescr) {
			visit((MVELExprDescr) descr);
		} else if (descr instanceof NotDescr) {
			visit((NotDescr) descr);
		} else if (descr instanceof OrDescr) {
			visit((OrDescr) descr);
		} else if (descr instanceof PackageDescr) {
			visit((PackageDescr) descr);
		} else if (descr instanceof PatternDescr) {
			visit((PatternDescr) descr);
		} else if (descr instanceof PredicateDescr) {
			visit((PredicateDescr) descr);
		} else if (descr instanceof QueryDescr) {
			visit((QueryDescr) descr);
		} else if (descr instanceof RelationalExprDescr) {
			visit((RelationalExprDescr) descr);
		} else if (descr instanceof RuleDescr) {
			visit((RuleDescr) descr);
		} else if (descr instanceof TypeDeclarationDescr) {
			visit((TypeDeclarationDescr) descr);
		} else if (descr instanceof TypeFieldDescr) {
			visit((TypeFieldDescr) descr);
		} else if (descr instanceof WindowDeclarationDescr) {
			visit((WindowDeclarationDescr) descr);
		} else if (descr instanceof NamedConsequenceDescr) {
			visit((NamedConsequenceDescr) descr);
		} else if (descr instanceof EvalDescr) {
			visit((EvalDescr) descr);
		} else if (descr instanceof BehaviorDescr) {
			visit((BehaviorDescr) descr);
		} else {
			throw new RuntimeException("xx DID NOT VISIT: " + descr.getClass().getName());
		}
	}

	/**
	 * Recursive visit descriptor to construct tree of LHS
	 * 
	 * @param descr
	 *            descriptor
	 * @param node
	 */
	public void visit(final Object descr, Node node) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		String lastMethodName = null;
		for (int i = 0; i < 5; ++i) {
			String thisMethodName = stack[i].getMethodName() + ":" + stack[i].getLineNumber();
			if (thisMethodName.equals(lastMethodName)) {
				fail("Infinite loop detected!");
			}
			lastMethodName = thisMethodName;
		}
		if (descr instanceof AccessorDescr) {
			visit((AccessorDescr) descr);
		} else if (descr instanceof AccumulateDescr) {
			visit((AccumulateDescr) descr);
		} else if (descr instanceof ActionDescr) {
			visit((ActionDescr) descr);
		} else if (descr instanceof AndDescr) {
			visit((AndDescr) descr, node);
		} else if (descr instanceof AnnotationDescr) {
			visit((AnnotationDescr) descr);
		} else if (descr instanceof AtomicExprDescr) {
			visit((AtomicExprDescr) descr);
		} else if (descr instanceof AttributeDescr) {
			visit((AttributeDescr) descr);
		} else if (descr instanceof BindingDescr) {
			visit((BindingDescr) descr);
		} else if (descr instanceof CollectDescr) {
			visit((CollectDescr) descr);
		} else if (descr instanceof ConstraintConnectiveDescr) {
			visit((ConstraintConnectiveDescr) descr);
		} else if (descr instanceof ExistsDescr) {
			visit((ExistsDescr) descr);
		} else if (descr instanceof ExprConstraintDescr) {
			visit((ExprConstraintDescr) descr, node);
		} else if (descr instanceof FactTemplateDescr) {
			visit((FactTemplateDescr) descr);
		} else if (descr instanceof FieldConstraintDescr) {
			visit((FieldConstraintDescr) descr);
		} else if (descr instanceof FieldTemplateDescr) {
			visit((FieldTemplateDescr) descr);
		} else if (descr instanceof ForallDescr) {
			visit((ForallDescr) descr);
		} else if (descr instanceof FromDescr) {
			visit((FromDescr) descr);
		} else if (descr instanceof FunctionDescr) {
			visit((FunctionDescr) descr);
		} else if (descr instanceof FunctionImportDescr) {
			visit((FunctionImportDescr) descr);
		} else if (descr instanceof GlobalDescr) {
			visit((GlobalDescr) descr);
		} else if (descr instanceof ImportDescr) {
			visit((ImportDescr) descr);
		} else if (descr instanceof LiteralRestrictionDescr) {
			visit((LiteralRestrictionDescr) descr);
		} else if (descr instanceof MVELExprDescr) {
			visit((MVELExprDescr) descr);
		} else if (descr instanceof NotDescr) {
			visit((NotDescr) descr);
		} else if (descr instanceof OrDescr) {
			visit((OrDescr) descr, node);
		} else if (descr instanceof PackageDescr) {
			visit((PackageDescr) descr);
		} else if (descr instanceof PatternDescr) {
			visit((PatternDescr) descr, node);
		} else if (descr instanceof PredicateDescr) {
			visit((PredicateDescr) descr);
		} else if (descr instanceof QueryDescr) {
			visit((QueryDescr) descr);
		} else if (descr instanceof RelationalExprDescr) {
			visit((RelationalExprDescr) descr);
		} else if (descr instanceof RuleDescr) {
			visit((RuleDescr) descr);
		} else if (descr instanceof TypeDeclarationDescr) {
			visit((TypeDeclarationDescr) descr);
		} else if (descr instanceof TypeFieldDescr) {
			visit((TypeFieldDescr) descr);
		} else if (descr instanceof WindowDeclarationDescr) {
			visit((WindowDeclarationDescr) descr);
		} else if (descr instanceof NamedConsequenceDescr) {
			visit((NamedConsequenceDescr) descr);
		} else if (descr instanceof EvalDescr) {
			visit((EvalDescr) descr);
		} else if (descr instanceof BehaviorDescr) {
			visit((BehaviorDescr) descr);
		} else {
			throw new RuntimeException("xx DID NOT VISIT: " + descr.getClass().getName());
		}
	}

	protected void visit(final AccessorDescr descr) {
		checkResource(descr);
		for (DeclarativeInvokerDescr d : descr.getInvokersAsArray()) {
			visit(d);

		}
	}

	protected void visit(final AccumulateDescr descr) {

		checkResource(descr);
		visit(descr.getInputPattern());
		for (BaseDescr d : descr.getDescrs()) {
			visit(d);
		}
	}

	protected void visit(final ActionDescr descr) {

		checkResource(descr);
	}

	protected void visit(final AndDescr descr) {
		checkResource(descr);
		for (BaseDescr baseDescr : descr.getDescrs()) {
			visit(baseDescr);
		}

	}

	/**
	 * Visit descriptor, create AND gate and recursive call for children
	 * 
	 * @param descr
	 * @param node
	 */
	protected void visit(final AndDescr descr, Node node) {
		checkResource(descr);
		if (!node.expNotNull())
			node.setGate(Gate.AND);
		for (BaseDescr baseDescr : descr.getDescrs()) {
			if (node.expNotNull())
				visit(baseDescr, node);
			else {
				Node child = new Node();
				node.addChildren(child);
				visit(baseDescr, child);
			}
		}

	}

	protected void visit(final AnnotatedBaseDescr descr) {
		checkResource(descr);
		for (BaseDescr annoDescr : descr.getAnnotations()) {
			visit(annoDescr);
		}
	}

	protected void visit(final AtomicExprDescr descr) {
		checkResource(descr);
	}

	protected RuleAttribute visit(final AttributeDescr descr) {
		RuleAttribute attr = new RuleAttribute(descr.getName(), descr.getValue());
		checkResource(descr);
		return attr;
	}

	protected void visit(final BindingDescr descr) {
		checkResource(descr);
	}

	protected void visit(final CollectDescr descr) {

		checkResource(descr);
		visit(descr.getInputPattern());
		for (BaseDescr d : descr.getDescrs()) {
			visit(d);
		}
	}

	protected void visit(final ConstraintConnectiveDescr descr) {
		checkResource(descr);
		for (BaseDescr d : descr.getDescrs()) {
			visit(d);
		}
	}

	protected void visit(final ExistsDescr descr) {
		checkResource(descr);
		for (Object o : descr.getDescrs()) {
			visit(o);
		}
	}

	protected void visit(final ExprConstraintDescr descr) {
		ConstraintConnectiveDescr result = drlexpparser.parse(descr.getExpression());
		RelationalExprDescr expr = (RelationalExprDescr) result.getDescrs().get(0);
		constraint = new Constraint();
		constraint.setLeft(expr.getLeft().toString());
		constraint.setOperand(expr.getOperator());
		constraint.setRight(expr.getRight().toString());
		expression.addConstraint(constraint);
		checkResource(descr);
	}

	/**
	 * Visit ExprConstraintDescr, create Constraint and added to Expression
	 * 
	 * @param descr
	 */
	protected void visit(final ExprConstraintDescr descr, Node node) {
		// logger.debug(descr.getExpression());
		ConstraintConnectiveDescr result = drlexpparser.parse(descr.getExpression());
		RelationalExprDescr expr = (RelationalExprDescr) result.getDescrs().get(0);
		constraint = new Constraint();
		constraint.setLeft(expr.getLeft().toString());
		constraint.setOperand(expr.getOperator());
		constraint.setRight(expr.getRight().toString());
		node.getExpression().addConstraint(constraint);
		logger.debug(node.getExpression().getConstraints().size());
		checkResource(descr);
	}

	protected void visit(final FactTemplateDescr descr) {
		checkResource(descr);
		for (FieldTemplateDescr d : descr.getFields()) {
			visit(d);
		}
	}

	protected void visit(final FieldConstraintDescr descr) {
		checkResource(descr);
		for (Object o : descr.getRestrictions()) {
			visit(o);
		}
	}

	protected void visit(final FieldTemplateDescr descr) {
		checkResource(descr);
	}

	protected void visit(final ForallDescr descr) {
		checkResource(descr);
		visit(descr.getBasePattern());
		for (BaseDescr o : descr.getDescrs()) {
			visit(o);
		}
	}

	protected void visit(final FromDescr descr) {
		checkResource(descr);
		for (BaseDescr d : descr.getDescrs()) {
			visit(d);
		}
	}

	protected void visit(final FunctionDescr descr) {
		checkResource(descr);
	}

	protected void visit(final FunctionImportDescr descr) {
		checkResource(descr);
	}

	protected void visit(final GlobalDescr descr) {
		checkResource(descr);
	}

	protected void visit(final ImportDescr descr) {
		checkResource(descr);
	}

	protected void visit(final LiteralRestrictionDescr descr) {
		checkResource(descr);
	}

	protected void visit(final MVELExprDescr descr) {
		checkResource(descr);
	}

	protected void visit(final NotDescr descr) {
		checkResource(descr);
		// NotDescr isn't type-safe
		for (Object o : descr.getDescrs()) {
			visit(o);
		}
	}

	protected void visit(final OrDescr descr) {

		checkResource(descr);
		for (BaseDescr d : descr.getDescrs()) {
			visit(d);
		}
	}

	/**
	 * Visit descriptor, create OR gate and recursive call for children
	 * 
	 * @param descr
	 * @param node
	 */
	protected void visit(final OrDescr descr, Node node) {
		if (!node.expNotNull())
			node.setGate(Gate.OR);
		checkResource(descr);
		for (BaseDescr d : descr.getDescrs()) {
			if (node.expNotNull())
				visit(d, node);
			else {
				Node child = new Node();
				node.addChildren(child);
				visit(d, child);
			}
		}
	}

	/**
	 * parse package descriptor and get functions
	 */
	protected Function parseFunction(FunctionDescr functionDescr) {
		Function function = new Function();
		function.setName(functionDescr.getName());
		function.setReturnType(functionDescr.getReturnType());
		int index = 0;
		for (String paramName : functionDescr.getParameterNames()) {
			Parameter param = new Parameter();
			param.setName(paramName);
			param.setType(functionDescr.getParameterTypes().get(index));
			index++;
			function.addParameter(param);
		}
		function.setBody(functionDescr.getBody());
		return function;
	}

	/**
	 * Visit package descriptor, create Ruleset
	 * 
	 * @param descr
	 * @return Ruleset
	 */
	protected RuleSet visit(final PackageDescr descr) {

		if (descr == null) {
			return null;
		}
		checkResource(descr);
		ruleset.setNameSpace(descr.getNamespace());
		for (ImportDescr importDescr : descr.getImports()) {
			logger.debug(" importDescr " + descr.toString());
			// add imports
			ruleset.addImport(importDescr.getTarget());
			// visit(importDescr);
		}
		for (FunctionImportDescr funcImportDescr : descr.getFunctionImports()) {
			logger.debug(" funcImportDescr " + descr.toString());
			// TODO
			visit(funcImportDescr);
		}
		for (AccumulateImportDescr accImportDescr : descr.getAccumulateImports()) {
			logger.debug(" accImportDescr " + descr.toString());
			visit(accImportDescr);
		}
		for (AttributeDescr attrDescr : descr.getAttributes()) {
			logger.debug(" attrDescr " + descr.toString());
			visit(attrDescr);
		}
		for (GlobalDescr globDesc : descr.getGlobals()) {
			logger.debug(" globDesc " + descr.toString());
			visit(globDesc);
		}
		for (FunctionDescr funcDescr : descr.getFunctions()) {
			ruleset.addFunction(parseFunction(funcDescr));
			logger.debug(" funcDescr " + descr.toString());
		}
		for (RuleDescr ruleDescr : descr.getRules()) {
			logger.debug(" ruleDescr " + descr.toString());

			ruleset.addRule(visit(ruleDescr, new Node()));
		}
		// add type declaration to ruleset model
		for (TypeDeclarationDescr typeDescr : descr.getTypeDeclarations()) {
			logger.debug(" typeDescr " + typeDescr.toString());
			TypeDeclaration td = new TypeDeclaration();
			// set name of declare object
			td.setName(typeDescr.getType().getFullName());
			// add parameters
			for (String key : typeDescr.getFields().keySet()) {
				logger.debug(key + " " + typeDescr.getFields().get(key));
				TypeFieldDescr fieldDescr = typeDescr.getFields().get(key);
				Parameter field = new Parameter();
				field.setName(fieldDescr.getFieldName());
				field.setType(fieldDescr.getPattern().getObjectType());
				td.addField(field);
			}
			// add declare
			ruleset.addDeclare(td);
			// visit(typeDescr);
		}
		for (EntryPointDeclarationDescr entryDescr : descr.getEntryPointDeclarations()) {
			logger.debug(" entryDescr " + descr.toString());
			visit(entryDescr);
		}
		for (WindowDeclarationDescr windowDescr : descr.getWindowDeclarations()) {
			logger.debug(" windowDescr " + descr.toString());
			visit(windowDescr);
		}
		for (EnumDeclarationDescr enumDescr : descr.getEnumDeclarations()) {
			logger.info(" enumDescr " + descr.toString());
			visit(enumDescr);
		}
		return ruleset;
	}

	protected void visit(final PatternDescr descr) {
		checkResource(descr);
		expression = new Expression();
		expression.setBindingType(descr.getIdentifier());
		expression.setObjectType(descr.getObjectType());
		visit(descr.getConstraint());
		for (BaseDescr behaDescr : descr.getBehaviors()) {
			visit(behaDescr);
		}

	}

	/**
	 * Visit PatternDescr, create expression and recrusive call with children
	 * 
	 * @param descr
	 */
	protected void visit(final PatternDescr descr, Node node) {
		checkResource(descr);
		// new expression
		expression = new Expression();
		expression.setBindingType(descr.getIdentifier());
		expression.setObjectType(descr.getObjectType());
		// add to the node
		node.setExpression(expression);
		// visit constraint
		visit(descr.getConstraint(), node);
		for (BaseDescr behaDescr : descr.getBehaviors()) {
			// visit children
			visit(behaDescr, node);
		}

	}

	protected void visit(final PredicateDescr descr) {
		checkResource(descr);
	}

	protected void visit(final QueryDescr descr) {
		checkResource(descr);
		visit(descr.getLhs());
		for (AttributeDescr attrDescr : descr.getAttributes().values()) {
			visit(attrDescr);
		}
	}

	protected void visit(final RelationalExprDescr descr) {
		checkResource(descr);
		visit(descr.getLeft());
		visit(descr.getRight());
	}

	/**
	 * Visit rule descriptor, create Rule
	 * 
	 * @param descr
	 * @param node
	 * @return
	 */
	protected Rule visit(final RuleDescr descr, Node node) {
		Rule rule = new Rule();
		// Set rule name
		rule.setName(descr.getName());
		checkResource(descr);
		// add rule attributes
		for (AttributeDescr d : descr.getAttributes().values()) {
			rule.addAttribute(visit(d));
		}
		// visit LHS
		visit(descr.getLhs(), node);
		// add LHS tree nodes
		rule.setLhs(node);
		// add consequences as list of String
		visitConsequence(descr.getConsequence(), rule);
		// TODO implement named consequence
		// for (Object o : descr.getNamedConsequences().values()) {
		// visitConsequence(o);
		// }
		return rule;
	}

	/**
	 * Visti consequence, add consequences as list of String to the rule
	 * 
	 * @param consequence
	 * @param rule
	 */
	protected void visitConsequence(final Object consequence, Rule rule) {
		// Basic implementation without recursive method based on split
		String[] cons = consequence.toString().trim().split(";");
		for (int i = 0; i < cons.length; i++) {
			Consequence con = new Consequence();
			con.setText(cons[i].trim());
			rule.getRhs().addConsequence(con);
		}
		// TODO implement visitor consequence based on descriptor
		// if (consequence instanceof BaseDescr) {
		// visit(consequence);
		// }
	}

	/**
	 * 
	 * @param descr
	 */
	protected void visit(final TypeDeclarationDescr descr) {
		checkResource(descr);
		for (TypeFieldDescr fieldDescr : descr.getFields().values()) {
			visit(fieldDescr);
		}
	}

	protected void visit(final TypeFieldDescr descr) {
		if (descr == null) {
			return;
		}
		checkResource(descr);
		visit(descr.getOverriding());
	}

	protected void visit(final WindowDeclarationDescr descr) {
		checkResource(descr);
		visit(descr.getPattern());
	}

	protected void visit(final NamedConsequenceDescr descr) {
		checkResource(descr);
	}

	protected void visit(final EvalDescr descr) {
		checkResource(descr);
	}

	protected void visit(final BehaviorDescr descr) {
		checkResource(descr);
	}

	/**
	 * Visit ruleset LHS (node) to create LHS descriptor, recursive call
	 * 
	 * @param node
	 * @param lhs
	 *            CEDescrBuilder<?, ?>
	 */
	protected void visit(Node node, CEDescrBuilder<?, ?> lhs) {
		// build pattern
		if (node.expNotNull()) {
			Expression expr = node.getExpression();
			PatternDescrBuilder<?> pattern = lhs.pattern(expr.getObjectType()).id(expr.getBindingType(), false);
			for (Constraint constr : expr.getConstraints()) {
				pattern.constraint(constr.stringFormat());
			}

		} else if (node.getGate().equals(Gate.AND)) {
			// build and descriptor
			CEDescrBuilder<?, ?> andDesc = lhs.and();
			for (Node n : node.getChildren()) {
				visit(n, andDesc);
			}

		} else if (node.getGate().equals(Gate.OR)) {
			// build or descriptor
			CEDescrBuilder<?, ?> orDesc = lhs.or();
			for (Node n : node.getChildren()) {
				visit(n, orDesc);
			}
		}
	}

	/**
	 * Add ruleset imports to pkg descriptor
	 * 
	 * @param pkg
	 * @param ruleset
	 */
	protected void addImportsToPkg(PackageDescrBuilder pkg, RuleSet ruleset) {
		for (String imp : ruleset.getImports())
			pkg.newImport().target(imp + ";");
	}

	/**
	 * Add ruleset type declares to pkg descriptor
	 * 
	 * @param pkg
	 * @param ruleset
	 */
	protected void addTypeDeclaresToPkg(PackageDescrBuilder pkg, RuleSet ruleset) {
		for (Function function : ruleset.getFunctions()) {
			FunctionDescrBuilder functionDescr = pkg.newFunction();
			functionDescr.returnType(function.getReturnType());
			functionDescr.name(function.getName());
			for (Parameter param : function.getParameters()) {
				functionDescr.parameter(param.getType(), param.getName());
			}
			functionDescr.body(function.getBody());
		}
	}

	/**
	 * Add ruleset functions to pkg descriptor
	 * 
	 * @param pkg
	 * @param ruleset
	 */
	protected void addFunctionsToPkg(PackageDescrBuilder pkg, RuleSet ruleset) {
		for (TypeDeclaration declare : ruleset.getDeclares()) {
			TypeDeclarationDescrBuilder typeDeclareDescr = pkg.newDeclare().type();
			typeDeclareDescr.name(declare.getName());
			for (Parameter param : declare.getFields()) {
				typeDeclareDescr.newField(param.getName()).type(param.getType());
			}
		}
	}

	/**
	 * Add rules from ruleset to pkg descriptor
	 * 
	 * @param pkg
	 * @param ruleset
	 */
	protected void addRulesToPkg(PackageDescrBuilder pkg, RuleSet ruleset) {
		for (Rule rule : ruleset.getRules()) {
			RuleDescrBuilder ruleDescr = pkg.newRule();
			ruleDescr.name(rule.getName());
			for (RuleAttribute rattr : rule.getAttributes())
				ruleDescr.attribute(rattr.getName(), rattr.getValue());
			visit(rule.getLhs(), ruleDescr.lhs());
			ruleDescr.rhs(rule.getRhs().buildRhs());
		}
	}

	/**
	 * Build package descriptor from ruleset
	 * 
	 * @param ruleset
	 * @return
	 */
	protected PackageDescr buildPackageDescriptorFromRuleSet(RuleSet ruleset) {
		PackageDescrBuilder pkg = DescrFactory.newPackage();
		pkg.name(ruleset.getNameSpace());
		addImportsToPkg(pkg, ruleset);
		addTypeDeclaresToPkg(pkg, ruleset);
		addRulesToPkg(pkg, ruleset);
		return pkg.getDescr();
	}

	protected void readTemplate(String drl) {
		InputStream is = new ByteArrayInputStream(drl.getBytes());
		TemplateContainer tc = new DefaultTemplateContainer(is);
		logger.info(tc.getHeader());
		for (String key : tc.getTemplates().keySet()){
			RuleTemplate rt = tc.getTemplates().get(key);
			logger.info(rt.getName());
			logger.info(rt.getContents());
			rt.getColumns();
		//	for(DefaultTemplateColumn dtc : rt.getColumns()){
				
			//}
		}

	}

}