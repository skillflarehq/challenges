model Plant
  "Lab test-rig translational fixture. Complete the connections, then simulate."
  // Instance parameters — do not change these values.
  parameter Real m(unit="kg") = 2.0 "Mass";
  parameter Real k(unit="N/m") = 50.0 "Spring stiffness";
  parameter Real d(unit="N.s/m") = 4.0 "Damping coefficient";
  parameter Real F(unit="N") = 10.0 "Constant applied force";

  Modelica.Mechanics.Translational.Components.Mass mass(
    m=m,
    s(start=0, fixed=true),
    v(start=0, fixed=true)) "Sliding mass, initially at rest at s = 0";
  Modelica.Mechanics.Translational.Components.Spring spring(c=k);
  Modelica.Mechanics.Translational.Components.Damper damper(d=d);
  Modelica.Mechanics.Translational.Components.Fixed wall;
  Modelica.Mechanics.Translational.Sources.ConstantForce force(f_constant=F);

  // Required topology (1D translational):
  //   wall ---- spring ---- mass <---- constant force
  //        ---- damper ----
  // Spring and damper in PARALLEL between the fixed wall and the mass.
  // The constant force acts on the mass (positive F increases s).
equation
  // TODO: write connect(...) statements for the topology above.
  // Do not add or remove components. Do not change parameter values.
end Plant;
