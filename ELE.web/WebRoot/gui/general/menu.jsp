<div style="float: left; z-index: 9999;">
	<ice:menuBar orientation="vertical">

		<ice:menuItem value="#{etiquetas.mnuInicio}"
			rendered="#{menuVista.renderInicioHab}" 
			action="goInicioAdmHab" />

		<ice:menuItem value="#{etiquetas.mnuInicio}"
			rendered="#{menuVista.renderInicioPlanchas}"
			action="goInicioAdmPlanchas" />
			
		<ice:menuItem value="#{etiquetas.mnuDesbloUser}"
			rendered="#{menuVista.renderDesbloUser}" 
			action="goDesbloUser" />

		<ice:menuItem value="#{etiquetas.mnuConsultarCabezaPlancha}"
			rendered="#{menuVista.indicadorVisibilidadOpcionPlanchas[0]}"
			action="#{menuVista.actionClickCabezaPlancha}" />
			
		<ice:menuItem value="#{etiquetas.mnuFormularios}"
			action="goFormularioReportesComite"
			rendered="#{menuVista.renderExpedirCumplimiento}">
		</ice:menuItem>

		<ice:menuItem value="#{etiquetas.mnuConsultarPlanchaEstado}"
			rendered="#{menuVista.indicadorVisibilidadOpcionPlanchas[1]}"
			action="goConsultaPlanchaEstado" />

		<ice:menuItem value="#{etiquetas.mnuCargarArchivoOtrasEntElect}"
			rendered="#{menuVista.indicadorVisibilidadOpcionPlanchas[2]}"
			action="goCargaArchivoOtrasEntElect" />

		<ice:menuItem value="#{etiquetas.mnuCargarArchivoSancionados}"
			rendered="#{menuVista.indicadorVisibilidadOpcionPlanchas[3]}"
			action="goCargaArchivoSancionados" />

		<ice:menuItem value="#{etiquetas.mnuMarcarPlanchaRecurso}"
			rendered="#{menuVista.indicadorVisibilidadOpcionPlanchas[4]}"
			action="goMarcarPlanchaRecurso" />

		<ice:menuItem value="#{etiquetas.mnuGenerarNumeracionPlanchas}"
			action="goGenerarNumeracionPlanchas"
			rendered="#{menuVista.renderNumerarPlancha}" />

		<ice:menuItem value="#{etiquetas.lblProcesoNumeracionPlanchas2}"
			action="goGenerarNumeracionPlanchasRecurso"
			rendered="#{menuVista.renderNumerarPlanchaEnRecurso}" />

		<ice:menuItem value="#{etiquetas.mnuRegistroVotantes}"
			rendered="#{menuVista.indicadorVisibilidadOpcionPlanchas[8]}"
			action="goRegistroVotantes" />

		<ice:menuItem value="#{etiquetas.lblMenuHabilidadAsociados}"
			rendered="#{menuVista.indicadorVisibilidadHabilidad}">
			
			<ice:menuItem value="#{etiquetas.lblMenuReportes}" 
				rendered="#{menuVista.indicadorVisibilidadOpcionHabilidad[2]}">
				
				<ice:menuItem value="#{etiquetas.lblSubMenuRepAsosHabinhab}"
					rendered="true" action="goGenerarReportesHabilidades" />
				<ice:menuItem value="#{etiquetas.lblReporteNovedades}"
					rendered="true" action="goConsultaNovedadesAplicadas" />
				<ice:menuItem value="Resumen Habilidades" rendered="true"
					action="goResumenHabilidad" />
				<ice:menuItem value="Resumen Novedades" rendered="true"
					action="goResumenNovedades" />
			</ice:menuItem>
			<!-- Se supriume la vista ejecutar proceso -->
			<!-- 
			<ice:menuItem value="#{etiquetas.mnuEjecutarProcesoHabilidad}"
				rendered="#{menuVista.indicadorVisibilidadOpcionHabilidad[0]}" action="goProcesoHabilidad" />
				 -->
			<ice:menuItem value="#{etiquetas.mnuRegistroNovedadesHabilidad}"
				rendered="#{menuVista.renderRegistroNovedadesHabilidad}" 
				action="goRegistroNovedadesHabilidad" />

		</ice:menuItem>

		<ice:menuItem value="#{etiquetas.mnuCargueSuspendidos}"
			action="goCargueSuspendidos"
			rendered="#{menuVista.indicadorVisibilidadOpcionHabilidad[1]}" />

		<ice:menuItem value="#{etiquetas.mnuCuociente}"
			rendered="#{menuVista.indicadorVisibilidadOpcionPlanchas[6]}">
			<ice:menuItem value="#{etiquetas.mnuCuocienteElectoral}"
				action="goCuocienteElectoral">
			</ice:menuItem>
			<ice:menuItem value="#{etiquetas.lblDelegadosZonaTitulo}"
				rendered="false"
				action="goDelegadosZona">
			</ice:menuItem>
		</ice:menuItem>

		<ice:menuItem value="#{etiquetas.mnuReporteJuego}"
			rendered="#{menuVista.indicadorVisibilidadOpcionPlanchas[9]}"
			action="goReporteJuego" />

		<ice:menuItem value="#{etiquetas.mnuModificacionPlanchas}"
			rendered="#{menuVista.renderModificacionPlanchas}"
			action="goModificacionPlanchas" />

		<ice:menuItem value="#{etiquetas.mnuSalir}"
			action="#{menuVista.salir}" />
	</ice:menuBar>
</div>