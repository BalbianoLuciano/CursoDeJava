#!/usr/bin/env zsh
# Compila y ejecuta un ejercicio de Java.
#   ./run.sh src/Practica/calculadorabase3                        (busca sola la clase con main)
#   ./run.sh src/Practica/calculadorabase3/CalculadoraBase3.java  (elegís la clase con main)

setopt null_glob

target="${1:-.}"
if [[ -f "$target" ]]; then
  dir="$(dirname "$target")"
  main_file="$target"
else
  dir="$target"
  main_file="$(grep -l 'static void main' "$dir"/*.java 2>/dev/null | head -1)"
fi

if [[ -z "$main_file" ]]; then
  echo "run.sh: no encontré ninguna clase con main en $dir"
  exit 1
fi

out="$(mktemp -d)"
trap 'rm -rf "$out"' EXIT

javac -d "$out" "$dir"/*.java || exit 1

pkg="$(sed -n 's/^package \(.*\);/\1/p' "$main_file" | head -1)"
cls="$(basename "$main_file" .java)"
java -cp "$out" "${pkg:+$pkg.}$cls"
