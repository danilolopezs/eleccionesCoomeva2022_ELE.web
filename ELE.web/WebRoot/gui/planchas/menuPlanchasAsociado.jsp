<ice:menuBar orientation="Horizontal" scrollableDivMode="true">
	<ice:menuItem value="#{etiquetas.mnuInicio}"
		rendered="#{menuVistaAsociado.renderInicioMenu}"
		action="goInicioMenu" />
	<ice:menuItem value="1. #{etiquetas.lblMnuRegistrarPlancha}"
		action="goRegistroPlanchaAsociado" />
	<!-- <ice:menuItem value="#{etiquetas.lblMnuConsultarPlancha}"
		action="goConsultarPlanchaAsociado" /> -->
	<ice:menuItem value="2. #{etiquetas.mnuinfocabezaplancha}"
		action="goRegistrarInfoCabezaPlancha" />
	<!--<ice:menuItem value="2. #{etiquetas.mnuinfosuplentePlancha}"
		action="goRegistrarInfoCabezaPlancha" />-->
	<ice:menuItem value="3. #{etiquetas.lblMenuEnviarRadicacion}"
		action="goEnviarRadicacion" />
	<ice:menuItem value="4. #{etiquetas.mnuSalir}"
		rendered="#{menuVistaAsociado.renderSalir}"
		action="#{menuPlanchaAsociado.salir}" />
</ice:menuBar>	